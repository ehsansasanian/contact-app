# --pro  dev test prod
# --port
# --action run build kill show
# --webapp true false

################################################################################################ Variables
appName='contact'
imageNameDefault='contact'
portDefault='9090'
webappDefault=false
exposePortDefault=false
################################################################################################
################################################################################################ 
################################################################################################ Create jar file name
jarName=$appName'-0.0.1-SNAPSHOT.jar'

################################################################################################ Convert arguments to variables
while [ $# -gt 0 ]; do
	if [[ $1 == *"--"* ]]; then
		v="${1/--/}"
		declare $v="$2"
	fi
	shift
done


################################################################################################ Check imageName agument
if ! [ -z "$imageName" ]
then
	imageNameDefault=$imageName
	echo $imageNameDefault
fi

################################################################################################ Check port agument
if ! [ -z "$port" ]
then
	portDefault=$port
	echo $portDefault
fi

################################################################################################ Check webapp agument
if ! [ -z "$exposePort" ]
then
	exposePortDefault=$exposePort
fi

################################################################################################ Check webapp agument
if ! [ -z "$webapp" ]
then
	webappDefault=$webapp
fi

############################################################################################### Build
if [ "$action" == "build" ]
then
	# remove target directory
	rm -rf target

	# webapp build
	if [ "$webappDefault" = true ]
	then
		cd src/webapp/
		ng build
		cd ../../
		rm -rf src/main/resources/static/
		mv target/classes/static/ src/main/resources/static/
		if ! [ $? -eq 0 ]; then
			echo "Build fail [web app build]"
			exit
		fi
	fi

	# Build
	mvn clean package -P$pro
	if ! [ $? -eq 0 ]; then
		echo "Build fail [mvn clean package -P$pro]"
		exit
	fi
fi

############################################################################################### Copy file
if [ "$action" == "run" ] ||  [ "$action" == "build" ]
then
	cp target/$jarName  target/app.jar
	if ! [ $? -eq 0 ]; then
		echo "Jar file not found!"
		exit
	fi
fi

############################################################################################### Profiles
############################################################################################### DEV
if [ "$pro" == "local" ]
then
	echo "DEV"
	####################################################################################### DEV Parameters
	path='/???/'$appName'/'
	serverIp='????'
	
	####################################################################################### Actions dev
	if [ "$action" == "run" ] ||  [ "$action" == "build" ]
	then
		# Remove old version
		ssh -p 10022 -t $serverIp "rm -rf $path ; kill -9 \$(lsof -t -i:$portDefault) ; sleep 5 ; mkdir -p $path ; exit ; bash"

		# Upload files
		scp -P 10022 target/app.jar $serverIp:$path
		if ! [ $? -eq 0 ]; then
			echo "Uploading error!"
			exit
		fi

		# Run jar file
		ssh -p 10022 -t $serverIp "tmux new -d 'cd $path ; java -jar app.jar' ; exit ; bash"
		if ! [ $? -eq 0 ]; then
			echo "Running new jar file error!"
			exit
		fi
	fi
	exit
################################################################################################ TEST
elif [ "$pro" == "test" ]
then
	echo "TEST"
	####################################################################################### TEST Parameters
#server : 185.37.55.235
	path=??
	serverIp=??



################################################################################################ PROD
elif [ "$pro" == "prod" ]
then
	echo "PROD"
	####################################################################################### PROD Parameters
	path='/home/dockeruser/deploy/microservice/'$appName'/'
	serverIp='dockeruser@94.232.173.145'

################################################################################################ DOC IN DEV
elif [ "$pro" == "dev-doc" ]
then
	echo "DOC IN DEV"
	####################################################################################### DOC IN DEV Parameters
	path='/root/deploy-doc/'$appName'/'
	serverIp='root@192.168.4.101'

################################################################################################ DOC IN DEV
elif [ "$pro" == "test-doc" ]
then
	echo "DOC IN TEST"
	####################################################################################### DOC IN DEV Parameters
	path='/root/deploy-doc/'$appName'/'
	serverIp='root@94.232.173.145'

################################################################################################ ELSE
else
	echo "Add profile argument.(--pro [dev || test || prod] )"
	exit
fi

############################################################################################### Actions docker
if [ "$action" == "run" ] ||  [ "$action" == "build" ]
then
	# Create directory
	ssh -p 10022 -t $serverIp "mkdir -p $path ; exit ; bash"

	# Copy files
	scp -P 10022 target/app.jar _deploy/Dockerfile _deploy/dockerbuild.sh $serverIp:$path

	# Run shell
	if [ "$exposePortDefault" = true ]
	then
		ssh -p 10022 -t $serverIp "cd $path ; chmod +x dockerbuild.sh; ./dockerbuild.sh --action run --imageName $imageNameDefault --hostPath $path --port $portDefault ; exit ; bash"
	else
		ssh -p 10022 -t $serverIp "cd $path ; chmod +x dockerbuild.sh; ./dockerbuild.sh --action run --imageName $imageNameDefault  --hostPath $path ; exit ; bash"
	fi

elif [ "$action" == "show" ]
then
	echo "show"
	rsync _deploy/dockerbuild.sh $serverIp:$path
	ssh -p 10022 -t $serverIp "cd $path ; chmod +x dockerbuild.sh; ./dockerbuild.sh --action show ; exit ; bash"

elif [ "$action" == "kill" ]
then
	echo "kill"
	rsync _deploy/dockerbuild.sh $serverIp:$path
	ssh -p 10022 -t $serverIp "cd $path ; chmod +x dockerbuild.sh; ./dockerbuild.sh --action kill --imageName $imageNameDefault ; exit ; bash"

else
	echo "Add action argument.(--action [run || build || kill || show])"
fi
