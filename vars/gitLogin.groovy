def call(String branch, String credentialsId, String repoUrl) {
    stage('Clone Private Repo') {
        git branch: branch, credentialsId: credentialsId, url: repoUrl
    }
}
