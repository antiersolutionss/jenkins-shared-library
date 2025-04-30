def dockerRegistryswrLogin(String credentialsId, String registry) {
    withCredentials([usernamePassword(credentialsId: credentialsId, usernameVariable: 'USR', passwordVariable: 'PSW')]) {
        sh """
            docker login -u $USR -p $PSW ${registry}
            echo "Logged in Registry successfully"
        """
    }
}
