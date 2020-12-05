<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>omorimapSub</title>
	<meta charset="UTF-8">
	<style>
		body {
			width :900px;
		}
		p.title {
			text-align:center;
		}
		p.shopname {
			text-align:center;
		}
		textarea.comments{
			width:300px;
			height:100px;
		}
		.cmntouter {
		text-align:center;
			height: 150px;
		}
		.cmntinner{
			vertical-align: top;
		}
		div.button {
			text-align:center;
		}
	</style>

	<script type="text/javascript">
	function btnsave(){
	  	ret = confirm("保存します。よろしいですか？");
	  	if (ret == true){
		  	location.href = "http://www.google.co.jp/";
	  	}
	}
	function btncancel(){
	  	ret = confirm("一覧画面に戻ります。よろしいですか？");
	  	if (ret == true){
		  	location.href = "http://www.google.co.jp/";
	  	}
	}
	</script>
</head>
<body>
	<p class="title">
		新規投稿＆編集画面
	</p>
	<form method="post">
		<p class="shopname">
			店舗名
			&nbsp;
			<input type = "text" name="shopname" style="width:300px;">
		</p>
		<div class="cmntouter">

				<span class="cmntinner">
					コメント
				</span>
				<span class="cmntinner">
					<textarea name="comments" class="comments">
					</textarea>
				</span>
		</div>
		<div class="button">
			<input type="submit" value="保存" onClick="btnsave()">
			&nbsp;
			<input type="button" value="キャンセル" onClick="btncancel()">
		</div>
	</form>
</body>
</html>