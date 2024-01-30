docker run -p 8080:8080 -v  /Users/psingh/.aws:/home/app/.aws --env-file /Users/psingh/CDMWorkspace/springbootskeltonapp/config/dev/environment.env -e AWS_ACCESS_KEY_ID=AKIASAD6BPHNJTFUUUBG -e AWS_SECRET_ACCESS_KEY=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 springbootskeltonapp:latest

docker run -p 8080:8080 -v  ~/.aws:/home/app/.aws/credentials --env-file /Users/psingh/CDMWorkspace/springbootskeltonapp/config/dev/environment.env  springbootskeltonapp:latest
