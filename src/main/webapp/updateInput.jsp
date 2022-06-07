<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>
  <div class="header">
    <h1 class="site_logo"><a href="menu.html">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">佐藤さん、こんにちは</p>
      <form class="logout_form" action="logoutServlet" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>

  <div class="insert">
  <div class="discription">
      <p>
        登録情報を入力してください（<span class="required"></span>は必須です）
      </p>
    </div>
  
    <div class="form_body">
       <c:if test="${not empty pdMsg}">
    		<p class="error">${pdMsg}</p>
   　　</c:if>
    <c:if test="${not empty upMsg}">
    		<p class="error">${upMsg}</p>
   　　</c:if>

      <form action="UpdateServlet" method="get">
        <fieldset class="label-130">
        <div>
            <input type="hidden" name="id" value="${fn:escapeXml(product.id)}" class="base-text">
          </div>
          <div>
            <label class="required">商品ID</label>
            <input type="text" name="productId" value="${fn:escapeXml(product.productId)}" class="base-text">
            <c:if test="${not empty idMsg}">
    			<ladel class="error">${idMsg}</ladel>
    		</c:if>
          </div>
          <div>
            <label class="required">商品名</label>
            <input type="text" name="productName" value="${fn:escapeXml(product.productName)}" class="base-text">
            <c:if test="${not empty nameMsg}">
     			<ladel class="error">${nameMsg}</ladel>
    		</c:if>
          </div>
          <div>
            <label class="required">単価</label>
            <input type="text" name="price" value="${fn:escapeXml(product.price)}" class="base-text">
            <c:if test="${not empty priceMsg}">
    			<ladel class="error">${priceMsg}</ladel>
    		</c:if>
          </div>
          <div>
            <label class="required">カテゴリ</label>
            <select name="categoryId" class="base-text">
              <option value="1">筆記具</option>
              <option value="2">オフィス機器</option>
              <option value="3">事務消耗品</option>
              <option value="4">紙製品</option>
              <option value="5">雑貨</option>
            </select>
          </div>
          <div>
            <label>商品説明</label>
            <textarea name="description" class="base-text">
            ${fn:escapeXml(product.description)}
            </textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="file">
            <span class="error">エラーメッセージ</span>
          </div>
        </fieldset>
          <div class="btns">
            <button type="button" onclick="openModal()" class="basic_btn">更新</button>
            <input type="button" onclick="location.href='./menu.jsp'" value="メニューに戻る" class="cancel_btn">
          </div>
          <div id="modal">
            <p class="modal_message">更新しますか？</p>
            <div class="btns">
              <button type="submit" class="basic_btn">更新</button>
              <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
            </div>
          </div>
      </form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<script src="./js/commons.js"></script>