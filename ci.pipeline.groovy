#!groovy
/*
  This ci.pipeline is the master of all other ci.pipeline
  with this ci.pipeline you can change the environment and the scope of the test
 */

pipeline {
    agent any

    // triggers {
    //     cron('H 02 * * *')
    // }

    // tools {
    //     maven 'maven'
    //     jdk 'jdk11'
    // }

    environment {
        //set the environment variable
        TEST_ENVIRONMENT = "${params.QAEnvironment}"
    }

    stages {
        stage('Compile') {
            steps {
                withMaven(
                        jdk: 'jdk11',
                        maven: 'maven'
                ) {
                    //On all environment we need to compile all tests
                    echo "------------- Start tests compilation packaging --------------"
                    script {
                        sh 'mvn clean compile'
//                         sh 'mvn -DskipTests=true package'
                    }
                    echo "-------------- End tests compilation --------------"
                }//end with maven
            }//end step
        }//end stage compilation

        // --------------------------------------------------------------------------------------------------------------------------
        stage('Run Test Suite') {
            steps {
                withMaven(
                        jdk: 'jdk11',
                        maven: 'maven',
                        options: [
                                junitPublisher(healthScaleFactor: 1.0)
                        ]
                ) {
                    echo "------------- Start suite test --------------"
                    script {
                        try {
                            sh 'mvn verify'
                        } catch (err) {
                            echo 'Error to run BDD suite test'
                        }
                    }
                    echo "-------------- End suite test --------------"
                }//end with maven
            }//end step
        }//end stage
        // --------------------------------------------------------------------------------------------------------------------------

    }//end stages


}
