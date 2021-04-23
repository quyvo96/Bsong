<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>

</div>
<!-- /. WRAPPER  -->
<!-- SCRIPTS -AT THE BOTOM TO REDUCE THE LOAD TIME-->
<!-- JQUERY -->

<!-- BOOTSTRAP SCRIPTS -->
<script src="<%=request.getContextPath()%>/resources/admin/assets/js/bootstrap.min.js"></script>
<!-- METISMENU SCRIPTS -->
<script src="<%=request.getContextPath()%>/resources/admin/assets/js/jquery.metisMenu.js"></script>
<!-- CUSTOM SCRIPTS -->
<script src="<%=request.getContextPath()%>/resources/admin/assets/js/custom.js"></script>
</body>
<script type="text/javascript">

	$(document).ready(function () {
		$('#form_login').validate({
			rules: {
				"username": {
					required: true,
					minlength:2,
					maxlength:32,
				},
				"password": {
					required: true,
					minlength:2,
					
				},

			},
			
			messages: {
			  "username": {
				required: "Vui lòng nhập Tên đăng nhập",
				minlength: " Nhập tối thiểu 6 ký tự",
				maxlength: "Nhập tối đa 32 kí tự",
			  },
			  "password": {
				required: "Vui lòng nhập Mật khẩu",
				minlength: " Nhập tối thiểu 6 ký tự",
			  },
			},
		});
	});
	$(document).ready(function () {
		$('#form_adver').validate({
			rules: {
				"name": {
					required: true,
					minlength:2,
					maxlength:32,
				},
				"web": {
					required: true,
					minlength:2,
					url: true,
				},
			},
			
			messages: {
			  "name": {
				required: "Vui lòng nhập Tên quang cao",
				minlength: " Nhập tối thiểu 3 ký tự",
				maxlength: "Nhập tối đa 32 kí tự",
			  },
			  "web": {
					required: "Vui lòng nhập web",
					minlength: " Nhập tối thiểu 6 ký tự",
				  },
			},
		});
	});
	$(document).ready(function () {
		$('#form_cat').validate({
			rules: {
				"name": {
					required: true,
					minlength:2,
					maxlength:32,
				},
			},
			
			messages: {
			  "name": {
				required: "Vui lòng nhập tên danh mục",
				minlength: " Nhập tối thiểu 3 ký tự",
				maxlength: "Nhập tối đa 32 kí tự",
			  },
			},
		});
	});
	$(document).ready(function () {
		$('#form_song').validate({
			rules: {
				"name": {
					required: true,
					minlength:2,
					maxlength:32,
				},
				"category": {
					required: true,
				},
				"preview": {
					required: true,
					minlength:2,
				},
				"detail": {
					required: true,
					minlength:2,
				},
			},
			
			messages: {
			  "name": {
				required: "Vui lòng nhập tên bài hát",
				minlength: " Nhập tối thiểu 3 ký tự",
				maxlength: "Nhập tối đa 32 kí tự",
			  },
			  "category": {
					required: "Vui lòng chọn danh mục",
				  },
			  "preview": {
					required: "Vui lòng nhập mô tả",
					minlength: " Nhập tối thiểu 3 ký tự",
			  },
			  "detail": {
					required: "Vui lòng nhập nội dung",
					minlength: " Nhập tối thiểu 3 ký tự",
			  },
			},
		});
	});
	$(document).ready(function () {
		$('#form_user').validate({
			rules: {
				"userName": {
					required: true,
					minlength:2,
					maxlength:32,
				},
				"fullName": {
					required: true,
					minlength:2,
					maxlength:32,
				},
				"password": {
					required: true,
					minlength:2,
					maxlength:32,
				},
			},
			messages: {
			  "userName": {
				required: "Vui lòng nhập Tên đăng nhập",
				minlength: " Nhập tối thiểu 3 ký tự",
				maxlength: "Nhập tối đa 32 kí tự",
			  },
			  "fullName": {
					required: "Vui lòng nhập Họ tên",
					minlength: " Nhập tối thiểu 3 ký tự",
					maxlength: "Nhập tối đa 32 kí tự",
			},
			  "password": {
					required: "Vui lòng nhập Mật Khẩu",
					minlength: " Nhập tối thiểu 3 ký tự",
					maxlength: "Nhập tối đa 32 kí tự",
			},
			},
		});
	});
</script>
</html>