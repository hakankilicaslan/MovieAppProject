FROM amazoncorretto:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]


#build->libs içindeki dosyamızın yolunu yazıp kullanıcı adı,image adı ve versiyon girerek bir image oluşturuyoruz.
#**********************************************************************************************************
#docker build --build-arg JAR_FILE=build/libs/MovieApp-0.0.1-SNAPSHOT.jar -t hakankilicaslan/movieapp:001 .
#**********************************************************************************************************

#Hostport ile containerportu yazıp yine kullanıcı adı,image adı ve versiyon girerek run edip container oluşturuyoruz.
#****************************************************
#docker run -p 9095:9090 hakankilicaslan/movieapp:001
#****************************************************

#Yukarıda önce database olarak denemek için önce h2database üzerinde çalıştık ama şimdi postgre üzerinde çalışacağız.
#Önce DockerHub içinden postgres diyerek database'in image'ini buluyoruz ve docker pull postgres komutunu kopyalıyoruz.
#Versiyon belirtmediğimiz için son sürümü yükleyecek kopyaladığımız komutu terminale yapıştırarak enter diyoruz.
#docker pull postgres diyerek postgre database'i lokale çekmiş olduk ve Docker Desktop içine image olarak eklendi.
#postgres image'ı pull ettikten sonra postgre için container oluşturacağız.
#docker run diyip sonra name kısmına container ismi password kısmına yml içine de girdiğimiz şifreyi girdik.
#Şifreyi kendimiz belirliyoruz ama iki tarafa da aynı giriyoruz sonra yayın yapacağımız port ve kendi portumuzu giriyoruz.
#En sona da DockerHub'tan çektiğimiz image neye aitse onu yazıyoruz ve java12postgre isminde bir container çalışıyor.
#********************************************************************************
#docker run --name java12postgre -e POSTGRES_PASSWORD=hakan -p 5454:5432 postgres
#********************************************************************************

#java12postgre container oluşturduktan sonra pgAdmin içine Java12Docker adında bir server oluşturduk.
#Server şifremizi burada belirttiğimiz şifre(hakan) ile aynı yaptık ve localhost olarakta 5454 girdik.
#MovieAppDockerDB isminde bir database oluşturduk ve App'imizi çalıştırdığımızda serverdaki database'e ulaşıp tablolar oluşturuldu.
#Bu şekilde lokalimizdeki server'a değilde 5454 olarak belirlediğimiz docker içindeki java12postgre adını verdiğimiz uzak sunucudaki container'a bağlandık


#*********************************************************DOCKER*********************************************************#
##Yukarıdaki denemelerden sonra artık bir network oluşturup network üzerinden containerları birbirine bağlayıp o şekilde çalıştıracağız.
#MovieApp ve Postgre için birer image daha sonra da birer container oluşturacağız.
#İkisi de farklı containerlarda ayağa kalkacağı için aralarında bir iletişim kuramayız.
#Bundan dolayı bir network oluşturup ikisini de aynı network üzerinden ayağa kaldırarak bağlantı kurmuş olacağız.

#İlk olarak network oluşturmak için docker network create dedikten sonra driver olarak bridge seçtik.
#subnet ve gateway olarak kendi belirlediğimiz IP adreslerini girdik ve en sona oluşturmak istediğimiz network adını yazdık.
#**************************************************************************************************
#docker network create --driver bridge --subnet 192.168.3.0/24 --gateway 192.168.3.1 java12-network
#**************************************************************************************************

#Docker Desktop içinde daha önceden bulunmuyorsa DockerHub üzerinden Docker Desktop içine postgres image çekiyoruz.
#********************
#docker pull postgres
#********************

#pgAdmin içine Java12Docker isminde bir server oluşturduk. username:postgres password:hakan localhost:5454 bilgilerini girdik.
#Oluşturduğumuz server içine MovieAppDockerDB isminde bir database oluşturduk. MovieApp projemizi son haline getirip build edeceğiz.
#Bundan dolayı yml dosyamıza gidip localhost:5432 yazan yere postgres için oluşturacağımız container ismini yani java12postgre yazıyoruz.
#username olarak postgres, password olarak server için verdiğimiz şifreyi yani hakan yazıyoruz ve database için açtığımız MovieAppDockerDB giriyoruz.

#yml dosyamız son haline geldiği için artık build işlemi yapabiliriz. Dockerfile isminde bir dosya oluşturuyoruz.
#build hatası almamak için ismini tam olarak Dockerfile şeklinde yazmamız gerekiyor. Daha sonra ilk 4 satırdaki kodlarımızı giriyoruz.
#Sağ taraftaki Gradle kısmından projemizin içindeki Tasks->build içinden sırasıyla clean-build-buildDependents yaparak son halini tekrar build ediyoruz.
#Daha sonra sol taraftaki build->libs içindeki dosyamızın yolunu yazıp kullanıcı adı,image adı ve versiyon girerek bir image oluşturuyoruz.
#**********************************************************************************************************
#docker build --build-arg JAR_FILE=build/libs/MovieApp-0.0.1-SNAPSHOT.jar -t hakankilicaslan/movieapp:001 .
#**********************************************************************************************************

#Artık postgres adında bir Postgre image ve hakankilicaslan/movieapp adında bir MovieApp projemizin image'larını oluşturmuş olduk.

#postgreyi oluşturduğumuz network üzerinden ayağa kaldıracağız. docker run komutlarımızı giriyoruz.
#İlk olarak yml içinde localhost yerine girdiğimiz postgre için oluşturacağımız container ismini yani java12postgre giriyoruz.
#Sonra server içinde verdiğimiz yml içine de yazdığımız şifremizi yani hakan yazıyoruz.
#Daha sonra önceden oluşturduğumuz network ismini yani java12-network yazıyoruz.
#Daha onra yayın yapacağımız yani server içine de girdiğimiz port ve kendi lokal portumuzu yazıyoruz.
#En sona da DockerHub'tan çektiğimiz image neye aitse yani Postgre'ye ait olan image ismini postgres yazıp çalıştırıyoruz.
#*****************************************************************************************************
#docker run --name java12postgre -e POSTGRES_PASSWORD=hakan --net java12-network -p 5454:5432 postgres
#*****************************************************************************************************

#Postgre image'ımızı network üzerinden ayağa kaldırdıktan sonra şimdi de MovieApp için oluşturduğumuz image'ı network üzerinden ayağa kaldıracağız.
#İlk olarak docker run komutu ve oluşturduğumuz network ismini yani java12-network yazıyoruz.
#Daha sonra yayın yapacağımız hostportu ve yml içine de girdiğimiz containerportu yazıyoruz.
#En sona da oluşturduğumuz image'ı kullanıcı adı,image adı ve versiyon şeklinde girip çalışıtırıyoruz.
#*************************************************************************
#docker run --net java12-network -p 9095:9090 hakankilicaslan/movieapp:001
#*************************************************************************

#Artık java12postgre adında bir Postgre container ve hakankilicaslan/movieapp image içinde adı ne olduysa container oluşturduk.
#Oluşturduğumuz containerlar java12-network üzerinden birbiriyle bağlantı kurabiliyorlar.