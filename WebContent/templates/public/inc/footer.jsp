<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
</div>

  <div class="footer">
    <div class="footer_resize">
      <p class="lf">Copyright &copy; <a href="#">VinaEnter Edu</a>. All Rights Reserved</p>
      <p class="rf">Code by Gia Huy - Khóa JAVA17</a></p>
      <div style="clear:both;"></div>
    </div>
  </div>
</div>
</body>
<script type="text/javascript">

	$(document).ready(function () {
		$('#sendemail').validate({
			rules: {
				"name": {
					required: true,
					minlength:2,
					maxlength:32,
				},
				"email": {
					email: true,
					required: true,
					minlength:2,
					
				},
				"website": {
					url: true,
					
				},
			},
			messages: {
			  "name": {
				required: "Vui lòng nhập Tên ",
				minlength: " Nhập tối thiểu 6 ký tự",
				maxlength: "Nhập tối đa 32 kí tự",
			  },
			  "email": {
				required: "Vui lòng nhập emali",
				minlength: " Nhập tối thiểu 6 ký tự",
				email:"Vui lòng nhập đúng định dạng",
			  },
			  "website": {
					url:"Vui lòng nhập đúng định dạng",
				  },
			},
		});
	});
</script>
</html>