def call() {
    pipeline {


        agent {
            label 'WORKSTATION'
        }

        triggers {

            pollSCM('*/1 * * * *')


        }


        stages{
            stage('Compile the code') {
                steps{
                    sh 'echo compile the code'
                }
            }
            stage('Test the code') {
                steps{
                    sh 'echo test the code'
                }
            }
            stage('Deploy the code') {
                steps{
                    sh 'echo deploy the code'
                }
            }
        }

    }


}


