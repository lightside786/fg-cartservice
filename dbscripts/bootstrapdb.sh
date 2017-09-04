#!/bin/bash

# Script is for Creating lightsidecart Database Tables. This script should be executed so that all users and roles are created for the DB.
# Based on where you execute this script you will need to get the host DB and power cart credentials for that DB. Any questions contact lightsidecompany@gmail.com
# You can also contact devs @slack #fi-lightsidecart

dbserver=$1;
username=$2;

if [ "$#" -eq 0 ]; then
	echo "Usage: $0 hostname User (User to be logged in as in my Sql Server)"
	echo "i.e. './$0 127.0.0.1 root"
	echo "This script creates all schemas as well as lightsidecart, lightsidesupport, and sa users to mirror prod, however all passwords are empty by default."
	exit 1
elif [ "$#" -eq 1 ]; then
	username="poweruser";
	echo "Usage: $0 hostname User (User to be logged in as in my Sql Server)"
	echo "i.e. './$0 127.0.0.1 root"
	echo "Script will be executed as poweruser for $1"
	echo "This script creates all schemas as well as lightsidecart, lightsidesupport, and sa users to mirror prod, however all passwords are empty by default."
elif [ "$#" -eq 2 ]; then
	echo "Script will be executed as $2 for $1"
	echo "This script creates all schemas as well as lightsidecart, lightsidesupport, and sa users to mirror prod, however all passwords are empty by default."
fi


read -s -p "Enter Password for root: " powerpassword

mysql -u$username  -h$dbserver -p$powerpassword << EOF

CREATE DATABASE fgcart DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;

CREATE USER 'fgcart'@'%' IDENTIFIED BY 'P@ssw0rd1234';
GRANT select, insert, delete, update, create, drop, index, references alter ON *.* TO 'fgcart'@'%';

EOF`