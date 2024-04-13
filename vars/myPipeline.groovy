def call() {
    pipeline {
        agent any
        stages {
             stage('Git Checkout') {
                 steps {
                     script {
                         git branch: 'node-dev', url: 'https://github.com/Yuvaraj9607/multi-branch.git' //project-repo
                     }
                 }
             }
            stage ('Build') {
                steps {
                    script {
                        sh 'npm install'
                    }
                }
            }
            // stage ('SonarQube Analysis') {
            //     steps {
            //         script { 
            //             def scannerHome = tool 'sonarscanner4'
            //             withSonarQubeEnv('sonar-pro') {
            //                 sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=node.js-app"
            //             }
            //         }
            //     }
            // }
            stage('Docker Build Images') {
                steps {
                    script {
                        sh 'docker build -t nodeapp/multi:v1 .'
                        sh 'docker images'
                        sh 'docker ps'
                    }
                }
            }
            // stage('Docker Push') {
            //     steps {
            //         script {
            //             withCredentials([string(credentialsId: 'dockerPass', variable: 'dockerPassword')]) {
            //                 sh "docker login -u yuvaraj9607 -p ${dockerPassword}"
            //                 sh 'docker push Multideploy/multi:v2'
            //                 sh 'docker rmi Multideploy/multi:v2'
            //             }
            //         }
            //     }
            // }
            // stage('Deploy on k8s') {
            //     steps {
            //         script {
            //             withKubeCredentials(kubectlCredentials: [[ credentialsId: 'kubernetes', namespace: 'ms' ]]) {
            //                 sh 'kubectl apply -f kube.yaml'
            //                 sh 'kubectl get pods -o wide'
            //             }
            //         }
            //     }
            // }
        }
    }
}
