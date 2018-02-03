pipeline {
    agent {
        docker {
            image 'maven:3'
             args '-v /root/.m2:/root/.m2'
         }
     }
     stages {
         stage('Build') {
             steps {
                 configFileProvider([configFile(fileId: '9ea7bb72-cb40-47e4-8af0-e7cb98aeb62c', variable: 'MAVEN_SETTINGS')]) {
                     sh 'mvn -B -s $MAVEN_SETTINGS clean package'
                 }
             }
         }
         stage 'All tests'
             mvn 'test -B -s $MAVEN_SETTINGS'
             step([$class: 'JUnitResultArchiver', testResults: '**/target/surefire-reports/TEST-*.xml'])
             if (currentBuild.result == "UNSTABLE") {
                 // input "Unit tests are failing, proceed?"
                 sh "exit 1"
             }
         stage('Publish') {
             steps {
                 configFileProvider([configFile(fileId: 'DeployMicroservice', variable: 'deploy')])
                 {
                  sshagent(credentials: ['a65d8fb8-0920-4060-a29d-2c46c3c2f994']){
                     sh 'chmod 775 $deploy'
                     sh '$deploy'
                 }
             }
             }
         }
     }
 }