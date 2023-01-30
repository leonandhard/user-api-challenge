#!/bin/zsh
root=$(cd "$(dirname "$0")"; pwd)
cd $root
echo "---build useapi image---"
docker build -t userapi:1.0 -f Dockerfile .

eval $(minikube docker-env)

kubectl delete "deployment/userapi"

minikube image load "userapi:1.0"
kubectl apply -f ./userapi.yaml