#!/usr/bin/env groovy

properties([
    buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5', artifactDaysToKeepStr: '5')),
    pipelineTriggers([[$class:"SCMTrigger", scmpoll_spec:"H/10 * * * *"]]),
])

def establishImage() {

    script {

        def img = docker.build("grindwiseinc/propertymgmt.house.register")
        echo "image created"

        img.inside {
            sh 'echo "image tests passed"'
        }

        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://registry.hub.docker.com', 'grindwiseinc') {
            img.push("${env.BUILD_NUMBER}")
            img.push("latest")
        }
    }
}

pipeline {
    agent any

    tools {
       gradle 'gradle3.5.1'
    }

    stages {
        stage('Commit') {

            steps {
               slackSend "started ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"

               echo "build clean shadowJar"
               sh 'gradle --refresh-dependencies clean shadowJar'
               sh 'gradle test'
               sh 'gradle findbugsMain'
               sh 'gradle checkstyleMain'
            }
        }

        stage('Container image') {
            steps {
                establishImage()
            }
        }
    }
    post {
        always {
            slackSend "ended ${env.JOB_NAME} ${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)"
        }
    }
}
