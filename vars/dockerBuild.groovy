def call() {
    echo "Building Docker image: ${imageName}"
    sh "ls"
    sh "sudo docker build -t env.REPO_URL ."
    echo "IMAGE BUILT SUCCESSFULLY!!"
}
