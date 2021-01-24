# omorimap

#### 【作成したWEBアプリケーション（ぐるなびの疑似簡易サイト）】<br>
※起動するまで少し時間がかかります<br>
https://blooming-sands-40247.herokuapp.com/<br>
<br>
#### 【アプリ名】<br>
　大森周辺マップ<br>
#### 【用途】<br>
　お店の位置、店舗名、コメント等を記録できるアプリ<br>
<br>
#### 【使い方】<br>
　以下、３つの操作ができます。<br>
　新規投稿<br>
　１．大森周辺マップの一覧表の上右端にある「新規投稿」ボタンを押下、新規投稿＆編集画面に画面遷移される<br>
　２．登録内容である、<br>
　　・地図上のマーカーを登録位置にドラッグ<br>
　　・店舗名の記入<br>
　　・カテゴリーの選択<br>
　　・評価の選択<br>
　　・コメントの記入<br>
　　を行い、下部にある「保存」ボタンを押下、大森周辺マップに画面遷移される<br>
　３．先程２．にて登録した内容について、<br>
　　・地図上にマーカー<br>
　　・一覧表最下行<br>
　　に対して２．で保存した情報が追加される<br>
　編集<br>
　１．大森周辺マップの一覧表の編集を行いたい行の行番号を押下、新規投稿＆編集画面に画面遷移される<br>
　２．先程１．にて選択した行の情報が各項目に事前に入力される<br>
　　編集したい箇所を書き換えて「保存」ボタンを押下、大森周辺マップに画面遷移される<br>
　３．先程２．にて登録した内容について、<br>
　　・地図上にマーカー<br>
　　・一覧表最下行<br>
　　に対して２．で保存した情報が追加される<br>
　削除<br>
　１．大森周辺マップの一覧表の削除を行いたい行のゴミ箱アイコンを押下<br>
　２．確認ダイアログにて"この行の情報を削除します。よろしいですか？"と聞かれるため、「OK」を選択<br>
　　選択した行の情報が非表示になる<br>
<br>
#### 【機能】<br>
　大森周辺マップ(Omorimap)<br>
　　地図<br>
　　　登録された各所の位置にマーカーが表示<br>
　　　（カーソルをマーカーに重ねると登録した店舗名が確認できる）<br>
　　一覧表<br>
　　　登録された各所の情報<br>
　　　・店舗名<br>
　　　・コメント（店舗名の下に表示）<br>
　　　・カテゴリー（飲食、ファッション、雑貨、サービス、その他）<br>
　　　・評価（5段階）<br>
　　　・最終更新日<br>
　　　・最終更新したクライアントのIPアドレス（最終更新日の下に表示）<br>
　　　　の表示<br>
　　　選択した行の編集と削除<br>
　新規投稿＆編集画面(OmorimapSub)<br>
　　地図<br>
　　　登録用のマーカーの設置<br>
　　登録フォーム<br>
　　　登録用のテキストボックス、プルダウンボックス、ラジオボタン<br>
　　　・店舗名<br>
　　　・カテゴリー<br>
　　　・評価<br>
　　　・コメント<br>
　　　各項目に合わせ設置<br>
<br>
#### 【開発環境】<br>
　言語：Java8<br>
　統合開発環境:Eclipse<br>
　DB：MySQL<br>
　　　heroku ClearDB MySQL（WEB公開用）<br>
　サーバー：tomcat8（ローカルテスト用）<br>
　　　　　　heroku dyno(WEB公開用)<br>
　地図：Leaflet（JavaScriptライブラリ）<br>
　　　　地理院地図（タイルベース）<br>
<br>
#### 【動作する各クラス、サーブレットの説明】<br>
　画面表示<br>
　　大森周辺マップ：Omorimap.java<br>
　　新規投稿＆編集画面:OmorimapSub.java<br>
　DBとの接続、SQL文処理をするクラス<br>
　　DAO.java<br>
　DBから取得した値を格納するクラス<br>
　　DTO.java<br>
　　　DBに登録しているlist（各店舗の情報）テーブルのレコード1行分を格納<br>
　　ListDTO.java<br>
　　　上記DTOををArrayListとして、まとめて格納<br>
　　Category_masterDTO.java<br>
　　　DBに登録してあるcategory_master（(1,"飲食")　等）のレコード一行を格納<br>
　　Category_masterListDTO.java<br>
　　　上記Category_masterDTOをArrayListとして、まとめて格納<br>
　Omorimap,OmorimapSub,DAOクラスの間で処理を行うサーブレット<br>
　　EntryServlet.java<br>
　クライアントIPv4アドレスを取得するクラス<br>
　　GetIp.java<br>
　登録、編集時の現在時刻を取得するクラス<br>
　　NowTime.java<br>
<br>
#### 【DBのテーブル、カラムの説明】<br>
　各店名の情報を記録<br>
　list テーブル<br>
　　id：インデックス (int,primary key,auto incremen, not null)<br>
　　no：大森周辺マップ(Omorimap)の各行番号 (int) (一覧表から削除された行番号は0)<br>
　　shopname：店舗名 (vachar(30))<br>
　　comments：コメント (vachar(100))<br>
　　dt：登録、編集日時 (date)<br>
　　ip：登録、編集クライアントIPv4アドレス(vachar(15))<br>
　　categoryno：カテゴリー番号(int,not null)<br>
　　star：評価 (int not null)<br>
　　latitude：登録、編集箇所の緯度 (double(16,14))<br>
　　longitude：登録、編集箇所の経度 (double(17,14))<br>
　カテゴリー名を格納<br>
　category_master テーブル<br>
　　categoryno：カテゴリー番号 (int,primary key,not null)<br>
　　categoryname：カテゴリー名 (vachar(10),not null)<br>
#### 【工夫、苦労して解決した点】<br>
　削除ボタンを押下時の処理について、SQLのDELETE文は使わず、非表示する仕様にしたこと<br>
　　・削除ボタンされたレコードのnoフィールドの値を0に変更<br>
　　・プログラム側でnoを再ソート<br>
　　・noが0ならOmorimapにて表示しない<br>
　新規投稿＆編集画面にて登録項目に記入しなかった場合のエラー表示<br>
　　・登録項目のrequestデータがnullの場合に、用意したerrMsg変数にエラーメッセージを格納<br>
　　・エラーメッセージと共に再表示<br>
　カテゴリー名の扱いについて<br>
　　各店舗のカテゴリー名を直接listテーブルのフィールドに登録するのではなく、<br>
　　カテゴリー名専用のテーブルcategory_masterテーブルを用意し、<br>
　　listテーブルのフィールドにはカテゴリー番号を登録し、結合できるようにして対応<br>
　地図表示にLeafletの使用<br>
　　地図ベースレイヤについて<br>
　　　地理院地図、地理院地図の淡色地図、オープンストリートマップの3つのうち、<br>
　　　オープンストリートマップを採用<br>
　　地図上のマーカーについて<br>
　　・クリックではなくドラッグして移動(draggable:true)<br>
　　・マーカーの位置情報の取得(例：lat = e.target._latlng.lat)<br>
　　・上記位置情報をhtmlで値を渡す(例：document.getElementById('lat').value= lat)<br>
　　							  (input type="hidden" name="latitude" id="lat")<br>
　herokuを使用してWEB公開<br>
　　omorimapプロジェクトにウェルカムページの作成<br>
　　　index.jspを追加し、起動時にOmorimap.javaに画面遷移するようするように記入<br>
　　MAVEN<br>
　　　Procfileの作成<br>
　　　pom.xmlの作成<br>
　　heroku ClearDB<br>
　　　設定時刻を日本時間に変更(heroku config:add TZ=Asia/Tokyo)<br>
　　　文字コードの設定(CLEARDB_DATABASE_URLに"?useUnicode=true&characterEncoding=utf8"の追記)<br>
　　　ゴミ箱アイコンをGitHubにアップロードし、URLを指定して表示<br>
　　　auto incrementが10ずつ飛ぶ仕様ため、対応するようプログラム側を修正(現在載せているコードに記載無)<br>
