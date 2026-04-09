node {
    def gitrepo ='https://github.com/rahulsng07/SpringBoot-Ticket-Booking.git'
    def stages = ['compile','test','package']
    
    stage('Git-clone'){
       git branch: 'main', 
       url: gitrepo 
    }
    for (i in stages)
    {
        stage(i)
        {
            if (i =='compile')
            {
                bat 'mvn compile'
            }
            if (i =='test')
            {
                bat 'mvn test'
                }
                if (i =='package')
                {
                bat 'mvn package'
                }
        }
    }
}
