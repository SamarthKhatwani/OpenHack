    Open Hack
******************

S3 Access URL
-----------------------
http://275openhack.s3-website.us-east-2.amazonaws.com/

Team Member:
-----------------------
1. Nirbhay Kekre 012455313 nirbhay.kekre@sjsu.edu
2. Rajat Dineshchandra Chaurasia 013734279 rajat.chaurasia@sjsu.edu
3. Samarth Khatwani 013592072 samarth.khatwami@sjsu.edu
4. Yash Kumar Mahajan 013766675 yashkumar.mahajan@sjsu.edu

Pre-requisite:
    Backend
        Maven and Java (1.8 or higher) should be installed.
        MySQL database server should be installed and up and runnging
        create a database named openHack, using following command:
            CREATE DATABASE openHack;
        Optional - eclipse
    Frontend
        ReactJs and npm should be installed
How To run
    Backend
        clone this repository
        Do necessary changes for database in application.properties file located in resource folder
        If you have eclipse installed, open the project in backend directory. And run it as spring boot application
        If eclipse is not installed, open terminal and run following command:
            mvn clean install -DskipTests
        go to target folder once build is successful
        run following command at terminal:
            java -jar open-hack-0.0.1-SNAPSHOT.jar

Commit History
-----------------------
commit f485ec85936148c48156f3789fa6b5b6fc964e08
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Tue May 21 22:28:49 2019 -0700

    Update README.md

commit 2047873e8c93e44f84808c39345c9a729cc54667
Merge: 8703637 a527f62
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 22:16:03 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 87036379e06e2333bf879f484cdd6e6ae6a95c5e
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 22:15:44 2019 -0700

    Readme.txt

commit a527f62ccef4f9a31528a0cca4e2acc0b3f545e6
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 22:09:19 2019 -0700

    s3 url added

commit 55a36db8d55d9683dbbda6f193ab14d3b7cdaf1a
Merge: 54f3b76 234740b
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 22:05:09 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 54f3b76d1a5e39b0851cde15f8b918e590daf8e5
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 22:05:02 2019 -0700

    sending leaderboard mail to judges

commit 234740bd8e38d45979965258af964a1034e30baf
Merge: ce1dfa7 265d57b
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 21:58:29 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit ce1dfa74b9a8410caef4a4867285338c45e0ddc3
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 21:58:26 2019 -0700

    Build and readme.txt

commit 265d57ba7a253fc0b043b9b5c2e1f173fa041ed5
Author: Yash Kumar Mahajan <yashmahajan19@gmail.com>
Date:   Tue May 21 21:55:39 2019 -0700

    Update README.md

commit f9f5da1ac0e9f48add727aaa5006b2adb12fcdce
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 21:35:47 2019 -0700

    missing changes

commit cba63c078e192aeabdd96fd9ee2549c728b21813
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 21:31:51 2019 -0700

    fixing listing judge view in hacker view

commit f66769370b2fc41aab0fd255d6ebde3494a243a6
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 21:19:27 2019 -0700

    Date issue fix

commit 9cb13561595a38db7a3e746dfd537fdbf4bad363
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 21:05:20 2019 -0700

    fixing date issue on frontend

commit 212720bc4f4932fe94534555a9d72a254b5c4351
Merge: 24a7130 2c24094
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 20:08:14 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 24a7130905a78a83db5b7db18b3300b07f0c7bc4
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 20:08:12 2019 -0700

    Add Expense and Leaderboard Update

commit 2c240946674d8027c8ea58133ab0a1d13f51134f
Merge: 5439d22 d9c2455
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 19:40:46 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 5439d227a03ca3b785582f3afe8c64ce453b81a8
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 19:40:35 2019 -0700

    fixing null pointer

commit d9c245572c6676ee4e44a06d061081bb45fb2f4b
Merge: ef8e5c5 a314b27
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 19:30:47 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit ef8e5c562de9dd76fefa397576d6cf33665f9216
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 19:30:45 2019 -0700

    Add Expense

commit a314b27e5911e007b6abfd5483e72d787752f462
Merge: e04d565 9e7abb3
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 19:02:22 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit e04d565918971e10861be79c6b5bbc818f4bcbc5
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 19:02:13 2019 -0700

    Financial report can be viewed even when hackathon is not finalized

commit 9e7abb392a155ca963114efbd0a303772f4d48aa
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 18:46:18 2019 -0700

    Update FinancialReport.js

commit 446d60053be23a69a6dee1c3e4998ebffd12ad93
Merge: 3aa4f56 7a766ee
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 18:32:01 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 3aa4f56bc53f5da24fd71c77b9558ce062430e23
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 18:31:56 2019 -0700

    Financial Report

commit 7a766ee1d3d01130c0dbe8834142ac37779ceea2
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 17:48:53 2019 -0700

    Date changing issue fix

commit 1ab80617f3e4ab210a85be7dfe4d287a466ea9a7
Merge: 2a3ffdd bd36176
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 16:39:34 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 2a3ffdd179e2a2b31108ad9749b7626a28473255
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 16:39:01 2019 -0700

    Leader board email

commit bd361762c1826be8ae116f071c9b18d579be3afc
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 16:38:05 2019 -0700

    LeaderBoard COmplete V1

commit 7cb80c375b08448e7264c1846b3f1d1d35d7d9c2
Merge: e98f819 5bd8297
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 16:11:23 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit e98f8198143c81705503fbad76cf187202058ff9
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 21 16:10:38 2019 -0700

    My Judgement and Leaderboard

commit 5bd829738c449d6144b456292811440917a6453f
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 15:46:24 2019 -0700

    detail hack for judge fix

commit 0919bd2b41e9dcdf4271629f0cdce3c4af0ec8fc
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 14:41:29 2019 -0700

    adding route for addExpense

commit b3e492d71c9e226ad0cda251fc6e9e369fa98a57
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 14:10:05 2019 -0700

    adding new aws instances info

commit 94dbcac99a411f841c9d9c34fc8bd1f12cd59e85
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 13:28:49 2019 -0700

    adding expenses to financial report

commit d6f0810ed4bb64b91065e9829b3a17de39a40a0d
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 21 12:07:56 2019 -0700

    adding financial report route

commit bbc6d1594bd764d33977b09dfe3f7a0de13b5ff8
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 22:43:07 2019 -0700

    storing time of payment and amount paid

commit 5bab0264f22a6bfd6b9e32d4621ecc1540713e62
Merge: cdcf00f 31c7462
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 20 22:07:49 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit cdcf00fbf8769c35c33e7b3912f97fd6bb4fb7e0
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 20 21:46:19 2019 -0700

    Comit before pull

commit 31c74627b1aa0547aba436ed2b555f1e4f0406d1
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 21:36:47 2019 -0700

    grade hackathon fix

commit f8dcc0140497e309e85506dd72c03a37fa820655
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 21:32:41 2019 -0700

    leader board fixes

commit 9029373039ffb4f090b978e5cc8434040c7764e2
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 20:02:21 2019 -0700

    detail view update

commit e32e6bbdab6c0438720a71a12af4ac5bf2775e5c
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 19:44:11 2019 -0700

    leaderBoardChanges

commit 74ad925d5b12ec2530c56be6fa2a34c31dbcadec
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 17:20:32 2019 -0700

    missing URI c hanges

commit f3af4701f27f37bcf01f68faa5e46305c3ae0495
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 17:15:32 2019 -0700

    checked proprty change on checbox

commit 054e6384dbe568a5e75d6045f6d69be6c2a5f3ee
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 16:46:26 2019 -0700

    frontend changes for detail view of hack when registering user

commit 9bed7ac6a34c869e0683014de97c2a6d7dfcba35
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 16:17:44 2019 -0700

    Frontend: Fixes for hackathon detail view

commit 848d299611122a10b6e632cfdc2a19dfeedeffee
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 16:02:34 2019 -0700

    adding missing changes in the interface

commit 05c606fda6d4fdcfc399cb1ecd4bcdfe8016d8b9
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 20 15:56:50 2019 -0700

    leader board api added, frontend: isOpen flag added on create/update hackathon

commit 75e253f6be73f28880e52fc700caa9bec6c427b3
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 13 20:30:33 2019 -0700

    fixing payment issues and removed Open date and close Date added isOpen flag

commit 449a5df0d85daa2d72305700c1475c565add2dfb
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 13 20:13:27 2019 -0700

    fetch price fix

commit 56430e0309bf5310448a5010e10a3dd1f5ee827a
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 13 15:14:45 2019 -0700

    grading route

commit f06a4cbf2fe4980166be467d3320f0cbe1953fa1
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 13 14:14:06 2019 -0700

    hackathon detail for judge

commit c8633187c97bf96bb4cd3ca61161a23591909b17
Author: Yash <yashmahajan19@gmail.com>
Date:   Wed May 8 18:17:31 2019 -0700

    Before Demo

commit 2c980f7896c9969a35a8274099ac76e2ed07c2c4
Merge: 6670000 8753d73
Author: Yash <yashmahajan19@gmail.com>
Date:   Wed May 8 17:04:08 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 6670000331294630d4b9fd9951395720b388aadf
Author: Yash <yashmahajan19@gmail.com>
Date:   Wed May 8 17:04:05 2019 -0700

    Detail Version

commit 8753d73088372b6ea65721cebde5774757761b26
Merge: 56cbc96 3b6cc55
Author: SamarthKhatwani <samarthkhatwani47@gmail.com>
Date:   Wed May 8 16:55:05 2019 -0700

    Merge pull request #20 from SamarthKhatwani/samarth_dev
    
    submit code

commit 3b6cc55f2ad377914b28f1c2f2590e79b719d410
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Wed May 8 16:53:46 2019 -0700

    submit code

commit 56cbc96be3d6ecc6b19673fce221a0305e7ab7e0
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 16:41:34 2019 -0700

    sending mail when every one paid the fee

commit 4b1ecc58956b0c92c2b0e74738ba869b9d2e48dc
Merge: 24c03bf 0152a14
Author: SamarthKhatwani <samarthkhatwani47@gmail.com>
Date:   Wed May 8 14:49:32 2019 -0700

    Merge pull request #19 from SamarthKhatwani/samarth_dev
    
    make payment

commit 0152a14fcadba5a4178615dee3b5415a009cf5b4
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Wed May 8 14:46:35 2019 -0700

    make payment

commit 24c03bf1340091ebc04d69a8a8746ae9e3d8fd08
Merge: 83ea75a a073b47
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 14:37:40 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 83ea75ae1a3f307d26932b2f1f16bb32d853bd4b
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 14:37:21 2019 -0700

    detail hackathon

commit a073b479dcc97b369b27766d99605b8536a79d41
Merge: 77718bd d413ae9
Author: Yash <yashmahajan19@gmail.com>
Date:   Wed May 8 14:11:42 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 77718bd395d3bb77a5a88408eb623d9176541851
Author: Yash <yashmahajan19@gmail.com>
Date:   Wed May 8 14:11:40 2019 -0700

    Hackathon List Version  1

commit d413ae9d4cda6e97287947c376ce445a73ef5cbb
Merge: 8cd0c80 12bae13
Author: SamarthKhatwani <samarthkhatwani47@gmail.com>
Date:   Wed May 8 12:56:53 2019 -0700

    Merge pull request #18 from SamarthKhatwani/samarth_dev
    
    fetch payment price

commit 12bae1388f850c75e49f41e9ec825190d8a474b6
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Wed May 8 12:53:09 2019 -0700

    fetch payment price

commit 8cd0c8058c949155ca1141eaf31d1594cae8130d
Merge: dc17169 516e7de
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 02:06:41 2019 -0700

    Merge branch 'nirbhay_dev'

commit 516e7de0d8162bbbba2a4ae14469927853fd573a
Merge: f1378fb 99118ab
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 02:05:01 2019 -0700

    Merge branch 'nirbhay_dev' of https://github.com/SamarthKhatwani/OpenHack into nirbhay_dev

commit f1378fbe7ad8d2a40a6472be7d1da505b1eb8ef8
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 02:04:52 2019 -0700

    payment url correction

commit dc17169fd5a6b44a24afa8157ee18796f813dd3a
Merge: eb4f140 99118ab
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Wed May 8 01:39:31 2019 -0700

    Merge pull request #17 from SamarthKhatwani/nirbhay_dev
    
    list hackathon changes

commit 99118ab293e3288cc57df814bccadf414c540177
Merge: 32b87a1 eb4f140
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Wed May 8 01:33:14 2019 -0700

    Merge branch 'master' into nirbhay_dev

commit 32b87a11397154b4582a62df6e14a66eb0d8714d
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Wed May 8 01:30:56 2019 -0700

    list hackathon changes

commit 2eb711e364033496d3f1525e8481a5fd698e18d4
Merge: 6d1cac7 eb4f140
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 22:51:15 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 6d1cac7a96dfcbe11f3e4bc2db4419879b94c20b
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 22:50:38 2019 -0700

    Revert "Update .gitignore"
    
    This reverts commit abb129a33c71318d41f0882fa5032d422a2a103c.

commit abb129a33c71318d41f0882fa5032d422a2a103c
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 22:50:18 2019 -0700

    Update .gitignore

commit 97b26b700ec6b58f1733a0819f422726e1208ff8
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 22:49:14 2019 -0700

    Update .gitignore

commit eb4f140fcdd0ce4680def93d1d64669c94816841
Merge: 62ff883 9326654
Author: Yash Kumar Mahajan <yashmahajan19@gmail.com>
Date:   Tue May 7 22:48:34 2019 -0700

    Merge pull request #16 from SamarthKhatwani/rajat_dev
    
    Rajat dev

commit 88dd5dfc654548c069380eadc3cf839a4b65d31f
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 22:45:58 2019 -0700

    Update Google Token

commit 932665456c7604d798fe52851e6b434b7c71b5a3
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Tue May 7 22:39:18 2019 -0700

    added screens

commit 62ff88325976fa493324103a0de33ab5bb25b154
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 7 22:29:40 2019 -0700

    minor changes in regester for hackathon route

commit d8349d9d056b872a27590c205bdd2467b7ca62cb
Merge: 927e67a 689246f
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Tue May 7 22:19:42 2019 -0700

    Merge pull request #15 from SamarthKhatwani/nirbhay_dev
    
    Entity updates and registration Hackathon changes

commit 689246f7a65793be2f49d89c2a9bed0b83904312
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Tue May 7 22:18:35 2019 -0700

    Entity updates and registration Hackathon changes

commit 927e67ade25285be202b30f1b77cbb8ca4fd4746
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 22:05:09 2019 -0700

    My Organization Version 1

commit 3184492ae5feb32b1cd255b131efe0701982e11e
Merge: 72c22fb 7b26446
Author: Rajat Chaurasia <theimagineer.rajat@gmail.com>
Date:   Tue May 7 21:15:41 2019 -0700

    Merge pull request #14 from SamarthKhatwani/master
    
    Merging master

commit 7b2644679936a1b3f357fb3d0f02b8dcacb02835
Merge: 7eeb147 8e5fed3
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 16:16:12 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 7eeb147810c77434ece16068f8743f8e640f0298
Author: Yash <yashmahajan19@gmail.com>
Date:   Tue May 7 16:16:06 2019 -0700

    My Organization

commit 8e5fed3310f39b1aacec5c5c91a8165dc5011d95
Merge: 361a495 72c22fb
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Tue May 7 15:27:35 2019 -0700

    Merge pull request #13 from SamarthKhatwani/rajat_dev
    
    Reject/Approve Request

commit 72c22fb967f6556a13b0ddc0f4f0fa0d377a20b3
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Tue May 7 15:26:12 2019 -0700

    Update OrganizationAuthAPI.java

commit 519cac026fb340dc86976067c65801cddfe5d670
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Tue May 7 15:19:02 2019 -0700

    organization request accept or reject

commit 4f3a46f5e7dbf88505835186d02c0246a11ac8eb
Merge: ab476f8 361a495
Author: Rajat Chaurasia <theimagineer.rajat@gmail.com>
Date:   Tue May 7 14:39:09 2019 -0700

    Merge pull request #12 from SamarthKhatwani/master
    
    Merge pull request #11 from SamarthKhatwani/rajat_dev

commit 361a4957268cfd76be1ba5ed9774b634843e6c68
Merge: 4714dee ab476f8
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Mon May 6 23:28:40 2019 -0700

    Merge pull request #11 from SamarthKhatwani/rajat_dev
    
    Issue fixes ListOrg Request

commit ab476f84f3d213f52e9fb863a82fdb4eeb627d68
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 23:26:28 2019 -0700

    fixing issues in Organization list route

commit 3109bc401e6b95dcff18b0f713ed6e5d3ef2d224
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 21:51:31 2019 -0700

    JsonIgnore Fix

commit c739ba7e65f9b446d8b925b9072b3a0d483f626b
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 21:42:15 2019 -0700

    Success flag issue fix

commit 8b3634e0977afa789bb9425ccffb55fded4c0945
Merge: 2797528 4714dee
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 20:52:29 2019 -0700

    merging master

commit 4714deefbfbddfb0378bac912a0be5513ac4ca46
Merge: 70c92d5 31477fa
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Mon May 6 20:44:24 2019 -0700

    Merge pull request #10 from SamarthKhatwani/nirbhay_dev
    
    fixing list Organization Request Route

commit 31477fad989031f538fcf8457bc6f96ad2715d00
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 20:43:14 2019 -0700

    fixing list Organization Request Route

commit c672302b7b1a1063dbbdb033b5aade4039af2080
Merge: 1f9b7e3 70c92d5
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 20:06:01 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 1f9b7e3223687f98b445f7028132ab011b7e58bc
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 20:05:58 2019 -0700

    My Organization Temp

commit 27975287a8ebaebac8ed93fac7a3e85dfaf32153
Merge: b0ebdb3 86ee198
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 20:04:06 2019 -0700

    Merge branch 'rajat_dev' of https://github.com/SamarthKhatwani/OpenHack into rajat_dev

commit b0ebdb3614a56ef98f8c5a2d683e7cb35da2a880
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 20:03:28 2019 -0700

    fixed an issue in getRequestList

commit 70c92d557e2158e2026ec4857b25128bf2236dcb
Merge: b7a0079 86ee198
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Mon May 6 19:37:11 2019 -0700

    Merge pull request #9 from SamarthKhatwani/rajat_dev
    
    Rajat dev

commit 86ee1987babe86d625a032ca53312c15dd1d314e
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 19:36:30 2019 -0700

    spring.jpa.hibernate.ddl-auto=update

commit 2fe6f9f4b3ba72c0f2b4c7043241ec493ff7ab46
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 19:10:41 2019 -0700

    get membership requests

commit b7a00796b68d34cc6c4cf7818aa2d9b5beeab6ad
Merge: f5f32dc 23c6601
Author: SamarthKhatwani <samarthkhatwani47@gmail.com>
Date:   Mon May 6 18:28:11 2019 -0700

    Merge pull request #8 from SamarthKhatwani/samarth_dev
    
    Samarth dev

commit 23c6601740eabd84d7f483a9e0f3b8a62dffd693
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 18:25:54 2019 -0700

    authorization claim admin verification

commit 6a797633d8fef59db2580986aa812f7ea609b4df
Merge: d39e42c f5f32dc
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 18:16:32 2019 -0700

    Merge branch 'master' into samarth_dev

commit f5f32dccfd39518060df4a91bfa96d5d348a22f2
Merge: eadb069 aeda077
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 17:54:55 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit eadb069cae4e1c6cee932aed8284cfd73d440d25
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 17:54:40 2019 -0700

    ORM organization changes

commit aeda077df6af81ddb20410bc200a6f3924beca9e
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 17:06:47 2019 -0700

    My Profile Version  1

commit 711df8a625a9e5707575bc8f115e62e5a39b2e0d
Merge: 8530369 dc1c942
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 16:28:19 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 8530369b928f118cc77984b2f654445ae41f915e
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 16:28:01 2019 -0700

    My Profile Temp 2

commit dc1c942cef66cad6c5bfbd34f0f306547a0f664c
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 16:27:41 2019 -0700

    autowire organization service

commit d39e42cd829793ad3f074af964c09d6ddb181b72
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 16:25:14 2019 -0700

    created claims aspect

commit cf2c5a5fca75a6bd30537b68564feae5f8cb058b
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 16:14:30 2019 -0700

    public access modifier

commit eeb67ee9e10fea8a3994312825a0479c14b42df9
Merge: 1045c83 e706fe6
Author: Rajat Chaurasia <theimagineer.rajat@gmail.com>
Date:   Mon May 6 16:02:25 2019 -0700

    Merge pull request #7 from SamarthKhatwani/rajat_dev
    
    Rajat dev

commit e706fe6b91abed92d9a424f78221864074351dc3
Merge: 0b1a953 1045c83
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 16:01:02 2019 -0700

    merge master

commit 0b1a9536d4dbabee09986b976b5d36ddf7ae1f81
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 15:54:11 2019 -0700

    create organization

commit a76432f09ce2979954556f05e14550af18f9e92e
Merge: 98dba47 1045c83
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 15:19:59 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit 98dba4750833bead74ea7a69de9cdddf61569dd9
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 15:19:57 2019 -0700

    My Profile TEmp

commit 1045c833e6d2a2bb13ec91dfeb2a2281b784b5bc
Merge: 9c0394c 8e16f8e
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Mon May 6 15:17:51 2019 -0700

    Merge pull request #6 from SamarthKhatwani/nirbhay_dev
    
    Admin routes Forbidden for non admin

commit 8e16f8e6ed71fb2730714f4e407bf8153fb2db19
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 15:16:59 2019 -0700

    Admin routes Forbidden for non admin

commit 9c0394c63bd994732617a1a0fd90ed85ccd87b72
Merge: 8aeb867 127f717
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Mon May 6 15:04:30 2019 -0700

    Merge pull request #5 from SamarthKhatwani/nirbhay_dev
    
    create update hackathon

commit 127f717b1fbab0cf9d6853698e89af9f108f363e
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 14:59:59 2019 -0700

    create update hackathon

commit 8aeb867cb2c7339411b948eebaf1c565f35523b9
Merge: 2ce57e6 3307f64
Author: SamarthKhatwani <samarthkhatwani47@gmail.com>
Date:   Mon May 6 04:02:41 2019 -0700

    Merge pull request #4 from SamarthKhatwani/samarth_dev
    
    Samarth dev

commit 3307f64a0e993e75eae76740556d96cc7cfe8654
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 04:00:38 2019 -0700

    changes to authentication aspect

commit 549ffa60a425b7c1ef8097ced9744a78e73b84b8
Merge: 6ad55c0 2ce57e6
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 03:58:19 2019 -0700

    Merge branch 'master' into samarth_dev

commit 2ce57e6a8a3ea0cdce0d3c0f4b69d5616ae37700
Merge: 364bf94 e9ee263
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Mon May 6 03:50:42 2019 -0700

    Merge pull request #3 from SamarthKhatwani/rajat_dev
    
    Rajat dev

commit e9ee2633e1ea5ed568cd9399a5ec108a654d3ab7
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 03:30:55 2019 -0700

    correcting route path

commit 6ad55c0b852c05e98bc9b1d405a5dc92cabd86b5
Merge: cdc289a 364bf94
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 03:28:46 2019 -0700

    Merge branch 'master' into samarth_dev

commit c65e5d1691cb9240eb9b13e773f04e401fc7b003
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 03:19:53 2019 -0700

    get organizations updated

commit 364bf94b3f1f8143cf1388a9b1b8f3c125475aab
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 02:29:01 2019 -0700

    SignIn/SignUp/Logout First Version

commit cdc289a0dca649f68a5f1e4d6a88e16d7ceb4636
Merge: 135369f 806e4ce
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Mon May 6 02:26:37 2019 -0700

    Merge branch 'master' into samarth_dev

commit 4ab7e522ed760e9bf4e295d653f7eed3c1c0edbc
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 02:02:00 2019 -0700

    began create org

commit 0d369262eb5b458f426e7f8d0f84798ea42878f2
Merge: a7db0ff 806e4ce
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 01:27:31 2019 -0700

    Merge branch 'master' of https://github.com/SamarthKhatwani/OpenHack

commit a7db0ffe9e7a7370061147e698d85758ba9d6efb
Author: Yash <yashmahajan19@gmail.com>
Date:   Mon May 6 01:27:26 2019 -0700

    Login/SignUp

commit 62366f8e2136bcc5f499fe145aa203d8c9c1d3f4
Merge: 6254450 806e4ce
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 01:01:33 2019 -0700

     merging master and resolving conflicts

commit 806e4ce89d32a0b3f7b30966bef4be7e4dfe6e4d
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 00:45:19 2019 -0700

    minor fix

commit 66656c69d81e5be134743a8cf61666103dfd37bc
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Mon May 6 00:36:53 2019 -0700

    create Profile

commit 6254450536ff4c58c1797e65ec56f2652195dcdf
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Mon May 6 00:15:10 2019 -0700

    get Organization

commit 135369f80217dbc9986f65da46cfe860e56ed1a6
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Sun May 5 21:10:10 2019 -0700

    firebase admin sdk poc

commit a5dd18f16227fe5e1a979283cf73a333c6df164d
Merge: df7f422 c8b519e
Author: Rajat Chaurasia <theimagineer.rajat@gmail.com>
Date:   Sun May 5 20:15:21 2019 -0700

    Merge pull request #2 from SamarthKhatwani/rajat_dev
    
    ScreenName lookup

commit c8b519e326a21f1276b6938a0d5c30f3f602f989
Author: RajatC13 <theimagineer.rajat@gmail.com>
Date:   Sun May 5 20:14:18 2019 -0700

    ScreenName lookup

commit df7f422ab5821516f9fa95b47d49cd65151e4dee
Merge: 42949b3 bcf1c0c
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Sun May 5 13:52:18 2019 -0700

    Merge branch 'nirbhay_dev'

commit bcf1c0c48ed06356bde1b649eecec865f4ca03d8
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Sun May 5 13:50:06 2019 -0700

    organization setting to null fix

commit 42949b3adae6fc2c807a53df0a2ea350b497f6bf
Merge: 5fb6b1f 0c71701
Author: Nirbhay Kekre <nirbhay.kekre@gmail.com>
Date:   Sun May 5 13:13:21 2019 -0700

    Merge pull request #1 from SamarthKhatwani/nirbhay_dev
    
    profile View and Update routes

commit 0c717011a0e2b22799f99a1b3a95f2c26321f428
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Sun May 5 02:20:20 2019 -0700

    profile View and Update routes

commit 5fb6b1fa6788fb876412dec9ec3524745e81479b
Author: Nirbhay Kekre <Nirbhay.Kekre@gmail.com>
Date:   Sat May 4 21:03:03 2019 -0700

    created Entities

commit d7255fddfb175b241e5889a2610373332acd89f8
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Fri May 3 20:22:24 2019 -0700

    starter Code

commit 22d686629f79dd594c39bb452c760b15b6ef5dda
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Fri May 3 20:03:13 2019 -0700

    Revert "test commit"
    
    This reverts commit c87374327baf19c29c05ee57f7a95f59e8489c68.

commit c87374327baf19c29c05ee57f7a95f59e8489c68
Author: Samarth Khatwani <SamarthKhatwani47@gmail.com>
Date:   Fri May 3 20:01:54 2019 -0700

    test commit

commit 2172f7d8bcf7b0859b7f30a27c3d22d5889f9394
Author: SamarthKhatwani <samarthkhatwani47@gmail.com>
Date:   Fri May 3 19:59:25 2019 -0700

    Initial commit
