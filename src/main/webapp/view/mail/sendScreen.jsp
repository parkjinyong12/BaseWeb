<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>CKEditor 5 – Document editor</title>
    <script src="https://cdn.ckeditor.com/ckeditor5/12.1.0/decoupled-document/ckeditor.js"></script>
    <style>
    	.subject {
    		margin: 20px 0px 20px 0px;    		
    	}
    	.subject .content-list {
    		width: 40%;
    	}
    	.subject .content-text {
    		font-size:18px;
    		width: 100%;
    	}
    	.subject .button-list {
    	    		
    	}		
    	.subject .subject-button {
    		font-size:12px;
    		padding: 0px 10px;
    		height: 26px;
    	}
    	.box {
    		display: inline-block;
    	}
    	#editor {
    		height: 600px;
    	}
    	.editorTd {
    		width:33.35pt;
    		border-top:none;
    		border-left:none;
    		border-bottom:solid windowtext 1.0pt;
    		border-right:solid windowtext 1.0pt;
    		padding:0cm 4.95pt 0cm 4.95pt;
    		height:16.5pt
    	}
    </style>
</head>
<body>
    <h1>Document editor</h1>
	<div class="subject">
		<div class="content-list box">
			<span>메일 제목을 적어주세요 >> </span><input id="mail-subject" class="content-text" type="text"/>
		</div>		
		<div class="button-list box">
			<input class="subject-button" type="button" value="전송" onclick="test();"/>
		</div>		
	</div>
    <!-- The toolbar will be rendered in this container. -->
    <div id="toolbar-container"></div>

    <!-- This container will become the editable. -->
    <div id="editor">
       	<p>5월 13일 점검리스트를 보내드립니다.</p>
       	<p>&nbsp;</p>
       	<figure class="table">
       		<table>
       			<tbody>
       				<tr>
       					<td>일자</td>
       					<td colspan="13">점검내역</td>
       					<td>&nbsp;</td>
       				</tr>
       				<tr>
       					<td rowspan="9">5월13일</td>
       					<td colspan="2">점검시각</td>
       					<td>22시 이전</td>
       					<td>9시</td>
       					<td>10시</td>
       					<td>11시</td>
       					<td>12시</td>
       					<td>13시</td>
       					<td>14시</td>
       					<td>15시</td>
       					<td>16시</td>
       					<td>17시</td>
       					<td>18시</td>
       					<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td rowspan="7">점검대상</td>
	       				<td>G/W</td>
	       				<td>&nbsp;</td>
	       				<td>정상</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td>수집</td>
	       				<td>&nbsp;</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td>실시간 Worker</td>
	       				<td>&nbsp;</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>정상</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td>통계 Batch</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td>DB</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td>WEB</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td>APP</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       				<tr>
	       				<td colspan="2">운행대수</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
	       				<td>&nbsp;</td>
       				</tr>
       			</tbody>
       		</table>
       	</figure>		
	</div>

    <script>
    	var newEditor
        DecoupledEditor
            .create( document.querySelector( '#editor' ) )
            .then( editor => {
            	newEditor = editor;
                const toolbarContainer = document.querySelector( '#toolbar-container' );

                toolbarContainer.appendChild( editor.ui.view.toolbar.element );
            } )
            .catch( error => {
                console.error( error );
            } );
        function test() {
        	
        	var loadForm = document.createElement('form'); 
    	    loadForm.setAttribute("method","post");
    	    loadForm.setAttribute("action","/BaseWeb/mail/sendSubmit.do");
    	          	    
    	    //addInFormHiddenObj(loadForm,"receiver",receiver);
            addInFormHiddenObj(loadForm,"subject",document.getElementById("mail-subject"));
            addInFormHiddenObj(loadForm,"content",newEditor.getData());
            //console.log(DecoupledEditor.getData());
            //console.log(document.getElementById("editor").getData());
            console.log(newEditor.getData());
    	    document.body.appendChild(loadForm);
    	    loadForm.submit(); 
        	
        }
        
        function addInFormHiddenObj(formObj, name, value) {
        	
        	var inputObj = document.createElement('input');
        	inputObj.setAttribute("type","hidden");
        	inputObj.setAttribute("name",name);
        	inputObj.setAttribute("value",value); 
        	
        	formObj.appendChild(inputObj);
        }
    </script>
</body>
</html>