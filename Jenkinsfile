#!/usr/bin/env groovy

properties([
    buildDiscarder(logRotator(numToKeepStr: '5', artifactNumToKeepStr: '5', artifactDaysToKeepStr: '5')),
    pipelineTriggers([[$class:"SCMTrigger", scmpoll_spec:"H/10 * * * *"]]),
])

def establishImage() {

    script {

        def img = docker.build("grindwiseinc/property.house.register")
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
               echo "build clean shadowJar"
               sh 'gradle clean shadowJar'
               //sh 'gradle findbugsMain'
               //sh 'gradle checkstyleMain'
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
            echo "post always"
        }
        success {
            echo "no downstream build required"
        }
        failure {
            echo 'This will run only if failed'
        }
        unstable {
            echo 'This will run only if the run was marked as unstable'
        }
        changed {
            echo 'This will run only if the state of the Pipeline has changed'
            echo 'For example, if the Pipeline was previously failing but is now successful'
        }
    }
}
