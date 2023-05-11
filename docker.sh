docker rmi -f $(docker images -q) 
docker buildx build --platform=linux/amd64  -t springbootskeltonapp:latest .
docker tag springbootskeltonapp:latest 928173699427.dkr.ecr.us-west-2.amazonaws.com/springbootskeltonapp:latest
aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 928173699427.dkr.ecr.us-west-2.amazonaws.com
docker push 928173699427.dkr.ecr.us-west-2.amazonaws.com/springbootskeltonapp:latest 
 
