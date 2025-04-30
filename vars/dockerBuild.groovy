def call() {
    echo "Building Docker image: "
    sh "ls"
    sh "sudo docker build -t env.NEXT_TAG ."
    echo "IMAGE BUILT SUCCESSFULLY!!"
}
