let app = angular.module('MyApp', ['ui.router']);

app.config(['$urlRouterProvider', '$stateProvider', function ($urlRouterProvider, $stateProvider) {
    $urlRouterProvider.otherwise('/');
    $stateProvider
        .state("home", {
            url: "/",
            templateUrl: "index.html"
        })
        .state('discussion', {
            url: '/discussionList',
            templateUrl: 'discussionList.html'
        })
        .state('discussionDetail', {
            url: '/view/discussion',
            templateUrl: "/view/discussion.html"
        })
        .state('profile', {
            url: '/view/profile',
            templateUrl: "/view/profile.html"
        })
        .state('help', {
            url: '/view/help',
            templateUrl: "/view/help.html"
        })
        .state('about', {
            url: '/view/about',
            templateUrl: "/view/about.html"
        })
}]);
app.controller('AppCtrl', function ($scope, $state, $http, $location) {
    $scope.courses = ['CS - 120 Java', 'CS - 741 Software Engineering', 'CS 743 - Software Verification and Validation', 'CS 451/551 - User Interface Design'];
    $scope.currentPage = $scope.courses[0];

    $scope.showLeft = true;
    $scope.data = {
        password: "",
        username: "",
        type: 0,
        errorMsg: "",
        currentCourse: "",
        discussionTopic: "",
        discussionImg: "",
        discussionContent: "",
        currentDiscussion: "",
        replyContent: "",
        comments: "",
    };

    $scope.images = ['/img/instructor.png','/img/beginner.png','/img/intermediate.png','img/expert.png'];

    $scope.reply = function () {
        let req = {
            method: 'POST',
            url: '/reply',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                content: $scope.data.replyContent, discussionId: $scope.data.currentDiscussion.id
            },
        };
        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                // console.log("data", response.data);
                if (!$scope.data.comments) {

                    $scope.data.comments = [];

                }
                response.data.user.avatar = $scope.images[response.data.user.type];
                $scope.data.comments.push(response.data);

            }
        );
    };
    $scope.discussions = null;

    $scope.user = JSON.parse(sessionStorage.getItem("user"));
    $scope.getCurrentUser = function () {
        let req = {
            method: 'GET',
            url: '/user',
        };
        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                // console.log("data", response.data);
                if (response.data) {
                    $scope.user = response.data;
                    $scope.user.avatar = $scope.images[$scope.user.type]// ? '/img/student.png' : '/img/teacher.png';
                    sessionStorage.setItem("user", JSON.stringify($scope.user))
                    $scope.selectCourse($scope.user.course[0].normalizedName);

                } else {
                    $scope.user = null;
                }

            },

            function (error) {
                $scope.hasError = true;
            }
        );
    };
    $scope.getCurrentUser();
    $scope.deleteDiscussion = function () {

    };
    $scope.loadImage = function (img) {
        $scope.data.discussionImg = img[0].name;
        // console.log(img[0]);
    };
    $scope.selectCourse = (course) => {
        $scope.data.currentCourse = course;
        // console.log(course);
        $scope.getDiscussion();
    };
    $scope.getDiscussion = () => {
        let req = {
            method: 'GET',
            url: '/discussion?course=' + $scope.data.currentCourse,
        };
        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                // console.log("data", response.data);
                if (response.data&&response.data.length>0) {
                    $scope.discussions = response.data;
                    for (let i = 0; i < $scope.discussions.length; i++) {
                        $scope.discussions[i].user.avatar = $scope.images[$scope.discussions[i].user.type]// ? '/img/student.png' : '/img/teacher.png';
                    }

                } else {
                    $scope.discussions = [];
                }

            },

            function (error) {
                $scope.hasError = true;
            }
        );
    };
    $scope.addDiscussion = function () {
        let req = {
            method: 'POST',
            url: '/discussion',
            headers: {
                'Content-Type': 'application/json'
            },
            data: {
                course: $scope.data.currentCourse,
                topic: $scope.data.discussionTopic,
                content: $scope.data.discussionContent,
                img: $scope.data.discussionImg
            },
        };
        // console.log(req);
        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                // console.log("data", response.data);
                $scope.getDiscussion();
                if (response.data) {

                } else {

                }
            },

            function (error) {
                $scope.hasError = true;
            }
        );
    };
    $scope.showDetail = function (discussion) {
        $scope.data.currentDiscussion = discussion;
        // console.log(discussion);
        $state.go('discussionDetail');
        let req = {
            method: 'GET',
            url: '/' + $scope.data.currentDiscussion.id + "/comment"
        };
        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                // console.log("data", response.data);
                $scope.data.comments = response.data;
                for (let i = 0; i < $scope.data.comments.length; i++) {
                    $scope.data.comments[i].user.avatar = $scope.images[$scope.data.comments[i].user.type]// ? '/img/student.png' : '/img/teacher.png';
                }
            },

            function (error) {
                $scope.hasError = true;
            }
        );
    };
    $scope.logout = function () {
        sessionStorage.removeItem("user");
        $scope.user = "";
        let req = {
            method: 'GET',
            url: '/logout',
        };
        $http(req).then(
            function (response) { // handles all status codes between 200-299.


            }
        );
    };
    $scope.login = function () {
        let req = {
            method: 'POST',
            url: '/login',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            data: 'username=' + $scope.data.username + '&password=' + $scope.data.password + '&type=' + $scope.data.type
        };
        $http(req).then(
            function (response) { // handles all status codes between 200-299.
                // console.log("data", response.data);
                if (response.data) {
                    $scope.user = response.data;
                    $scope.user.avatar = $scope.images[$scope.user.type]// ? '/img/student.png' : '/img/teacher.png';
                    $scope.data.errorMsg = "";
                    sessionStorage.setItem("user", JSON.stringify($scope.user));
                    $scope.selectCourse($scope.user.course[0].normalizedName);


                } else {
                    $scope.data.errorMsg = "INVALID USERNAME / PASSWORD / TYPE";
                }

            },

            function (error) {
                $scope.hasError = true;
            }
        );
    };
    $scope.selectedDeleteDiscussion="";
    $scope.selectDeleteDiscussion = function (di) {
        $scope.selectedDeleteDiscussion=di;
    };

    $scope.deleteDiscussion = function () {
        if($scope.selectedDeleteDiscussion){

            let req = {
                method: 'DELETE',
                url: '/discussion/'+$scope.selectedDeleteDiscussion.id,
            };
            $http(req).then(
                function (response) {
                    $scope.selectCourse($scope.data.currentCourse)

                }
            );
        }

    }

});

