aws ecr get-login-password --region us-west-2 | docker login --username AWS --password-stdin 928173699427.dkr.ecr.us-west-2.amazonaws.com
docker build -t springbootskeltonapp:latest .
docker tag springbootskeltonapp:latest 928173699427.dkr.ecr.us-west-2.amazonaws.com/springbootskeltonapp:latest
docker push  928173699427.dkr.ecr.us-west-2.amazonaws.com/springbootskeltonapp:latest
