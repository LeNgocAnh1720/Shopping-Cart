
var app = angular.module("product-app",[]);
app.controller("product-ctrl",function($scope,$http){
    $scope.items=[];
    $scope.cates = [];
    $scope.form = {};

    $scope.initialize = function(){
        //Load Product
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            //Chuyển ngày về date JavaScript
            $scope.items.forEach(item => {
                item.createDate = new Date(item.createDate)
            })
        });

        //Load categories
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
    }

    //Start---------------------------------------------------------------------//
    $scope.initialize();

    //Delete Form
    $scope.reset = function(){
        $scope.form = {
            createDate: new Date(),
            image:'cloud-upload.jpg',
            available:true,
        };
    }

    //Display Form
    $scope.edit = function(item){
        $scope.form = angular.copy(item);
        $(".nav-tabs .nav-item button.nav-link:eq(0)").tab('show');
    }

    //Save Product Form
    $scope.create = function(){
        var item = angular.copy($scope.form);
        $http.post('/rest/products',item).then(resp =>{
            resp.data.createDate = new Date(resp.data.createDate);
            $scope.items.push(resp.data);
            $scope.reset();
            alert("Thêm mới sản phẩm thành công")
        }).catch(error => {
            alert("Thêm mới thất bại")
            console.log("Error",error);
        });
    }

    //Update Product Form
    $scope.update = function(){
        var item = angular.copy($scope.form);
        $http.put(`/rest/products/${item.id}`,item).then(resp => {
            var index = $scope.items.findIndex(p => p.id == item.id);
            $scope.items[index] = item;
			$scope.reset();
            alert("Cập nhật thành công");
        }).catch(error => {
            alert("Cập nhật thất bại")
            console.log("Error",error);
        });
    }

    //Delete Form
    $scope.delete = function(item){
        $http.delete(`/rest/products/${item.id}`).then(resp => {
			var index = $scope.items.findIndex(p => p.id ==item.id);
			$scope.items.splice(index,1);
			$scope.reset();
			alert("Xóa thành công");
		}).catch(error => {
            alert("Xóa thất bại")
            console.log("Error",error);
        });
    }

    //Update Image
    $scope.imageChanged = function(files){
		var data = new FormData();
		data.append("file",files[0]);
		$http.post('/rest/upload/product-img',data,{
			transformRequest: angular.identity,
			headers:{'Content-Type':undefined}
		}).then(resp =>{
			$scope.form.image = resp.data.name;
		}).catch(error =>{
			alert('Lỗi Upload hình ảnh');
			console.log("Error",error);
		})
    }

    //PHÂN TRANG-------------------------------------
    $scope.pager = {
        page: 0,
        size: 10,
        get items(){
            var start = this.page * this.size;
			var end = start+this.size;
            return $scope.items.slice(start,end);
        },
		
		get count(){
			return Math.ceil(1.0 * $scope.items.length / this.size);	
		},
		
		first(){
			this.page = 0;
		},
		
		prev(){
			this.page--;
			if(this.page<0){
				this.last();
			}
		},
		
		next(){
			this.page++;
			if(this.page >= this.count){
				this.first();
			}
		},
		
		last(){
			this.page = this.count - 1 ;
		}
    }
});