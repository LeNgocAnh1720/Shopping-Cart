 var app = angular.module("authority-app",[]);
app.controller("authority-ctrl",function($scope,$http,$location){
    $scope.roles=[];
    $scope.admins=[];
    $scope.authorities=[];

    $scope.initialize = function(){
        //Load All roles
        $http.get("/rest/roles").then(resp => {
            $scope.roles = resp.data;
        })

        //Load staff and directors
        $http.get("/rest/accounts?admin=true").then(resp => {
            $scope.admins = resp.data;
        })

        //Load authorites of staffs and director
        $http.get("/rest/authorities?admin=true").then(resp => {
            $scope.authorities = resp.data;
        }).catch(error => {
            $location.path("/unauthorized");
        })

        $scope.authority_of = function(acc,role){
            if($scope.authorities){
                return $scope.authorities.find(ur => ur.account.username == acc.username && ur.role.id == role.id);
            }
        }

        $scope.authority_changed = function(acc,role){
            var authority = $scope.authority_of(acc,role);
            if(authority){ //Nếu đã cấp quyền => thu hồi quyền xóa
                $scope.revoke_authority(authority);
            }else{ //Chưa cấp quyền => cấp quyền thêm mới
                authority = {account:acc, role:role};
                $scope.grant_authority(authority);
            }
        }

        //THÊM MỚI AUTHORITY
        $scope.grant_authority = function(authority){
            $http.post(`/rest/authorities`,authority).then(resp => {
                $scope.authorities.push(resp.data);
                alert("Cấp quyền sử dụng thành công")
            }).catch(error =>{
              alert("Cấp quyền sử dụng thất bại");
              console.log("Error",error);  
            })
        }

        //XOA AUTHORITY
        $scope.revoke_authority = function(authority){
            $http.delete(`/rest/authorities/${authority.id}`).then(resp => {
                var index = $scope.authorities.findIndex(a => a.id == authority.id);
                $scope.authorities.splice(index,1);
                alert("Thu hồi quyền thành công");
            }).catch(error => {
                alert("Thu hồi quyền thất bại");
                console.log("Error",error);
            })
        }
    }

    $scope.initialize();
});