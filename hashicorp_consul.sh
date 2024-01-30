#!/bin/bash
input="./src/main/resources/appconfig.properties"
while IFS= read -r line
do
    key=${line%%=*}
    value=${line#*=}
    echo $key
    curl --request PUT --data "$value"  http://127.0.0.1:8500/v1/kv/config/SPRING-SKELTON-APP/$key

done < $input 
