<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <title>View Books</title>
        <link href="<c:url value="/css/common.css"/>" rel="stylesheet" type="text/css">
        <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css"
        rel="stylesheet">
    </head>
    <body>

      <div class="container">
        <div class="row">
          <div class="col-md-12">
            <nav class="navbar navbar-inverse navbar-fixed-top">
              <div class="container-fluid">
                  <!-- Brand and toggle get grouped for better mobile display -->
                  <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                      <span class="sr-only">Toggle navigation</span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                      <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">Brand</a>
                  </div>

                  <!-- Collect the nav links, forms, and other content for toggling -->
                  <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                      <li class="active"><a href="#">Menu <span class="sr-only">(current)</span></a></li>
                      <li><a href="#">Categorie</a></li>

                    </ul>

                    <ul class="nav navbar-nav navbar-right">
                        <div class="navbar-form navbar-left">
                          <div class="form-group">
                            <input type="text" class="form-control" placeholder="Search" name="title" id="ftitle">
                          </div>
                          <button type="submit" class="btn btn-default" onclick="searchFilm()"><div style="font-size: 20px;"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></div></button>
                        </div>
                    </ul>
                  </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
          </div>
        </div>
        <div class="row" style="padding-top: 50px;">
          <div class="col-md-9">
            <table  class="table table-striped">
              <caption><h3>Liste des films disponibles</h3></caption>
                <thead>
                    <tr>
                        <th>Photo</th>
                        <th>Details</th>
                    </tr>
                </thead>
                <tbody id="liste">

                </tbody>
            </table>
          </div>
          <div class="col-md-3">
            <table class="table table-striped">
              <tr>
                <th>
                  <h3 class="text-success">
                    <span class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></span> Panier
                  </h3>
                </th>
              </tr>
              <tr>
                <td>
                  <div class="row">
                    <div class="col col-md-4">Montant</div>
                    <div class="col col-md-8" id="fmontant">15Ar</div>
                  </div>
                </td>
              </tr>
              <tr>
                <td>
                  <div class="row">
                    <div class="col col-md-4">Volume</div>
                    <div class="col col-md-8">
                      <div class="row">
                        <div class="col col-md-6" id="vmo">100000Mo</div>
                        <div class="col col-md-6" id="vgo">0Go</div>
                      </div>
                    </div>
                  </div>
                </td>
              </tr>
              <tr>
                <td>Liste des films : <br>
                  <ul class="list-group list-group-flush" id="pliste">
                  </ul>
                </td>
              </tr>
            </table>
          </div>
        </div>


      </div>
      <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
      <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
      <script type="text/javascript">
        var getM = "GET";
        var postM = "POST";
        function prepare(data) {
          let contents = '';
          let listes = JSON.parse(data);
          for (var i = 0; i < listes.length; i++) {
            contents += '<tr>';
            contents += '<td><img src="data:image/jpeg;base64,'+listes[i].image+'" width="200"></td>';
            contents += "<td><h2>" +listes[i].title+ "</h2>";
            contents += "<div>" +listes[i].description+ "</div>";
            contents += '<div style="font-size:10px;\">' +listes[i].datesortie+ '</div>';
            contents += '<div style="padding-top:20px;"><a class="btn btn-success" onclick="add('+listes[i].idfilm+')">Ajouter</a></div></td>';
            contents += '</tr>';
          }
          $("#liste").html(contents);
        }

        function add(id) {
          let urls = '/add';
          urls += '?id='+id;
          getPanier(urls,getM);
        }

        function sup(id) {
          let urls = '/del';
          urls += '?id='+id;
          getPanier(urls,getM);
        }

        function preparePanier(data) {
          console.log(data);
          let listes = JSON.parse(data);
          $('#fmontant').html(listes.montant+'Ar');
          $('#vmo').html(listes.volumemo+'Mo');
          $('#vgo').html(listes.volumego+'Go');
          let contents = '';
          for (var i = 0; i < listes.film.length; i++) {
            contents += '<li class="list-group-item"><div class="row"> <div class="col-md-3">';
            contents += '<img src="data:image/jpeg;base64,'+listes.film[i].image+'" width="50">';
            contents += '</div> <div class="col-md-7"><h4>'+listes.film[i].title+'</h4></div> ';
            contents += '<div class="col-md-2"><h4><a onclick="sup('+listes.film[i].idfilm+')"><span class="glyphicon glyphicon-remove text-danger" aria-hidden="true"></span></a></h4></div></div> </div></li>';
          }
          $('#pliste').html(contents);
        }

        function getPanier(urls,method) {
          $.ajax({
            url:urls,
            type:method
          }).then(function(data) {
            preparePanier(data);
          });
        }

        function loadPanier() {
          getPanier('/panier',getM);
        }

        function ask(urls,method) {
          $.ajax({
            url:urls,
            type:method
          }).then(function(data) {
            prepare(data);
          });
        }

        function search(inp,urls,method) {
          let contents = '';
          for (var i = 0; i < inp.length; i++) {
            contents += ((i != 0)? '&' : '') +inp[i].tag +"="+ $(inp[i].cmp).val();
          }
          urls += '?' + contents;
          ask(urls,method);
        }

        function searchFilm() {
          let inp = [{
            'tag' : 'title',
            'cmp' : '#ftitle'
          }];
          let urls = '/search';
          search(inp,urls,getM);
        }

        $('#ftitle').keyup(function () {
          searchFilm();
        });

        $(document).ready(function() {
          ask("getall",getM);
          loadPanier();
        });

      </script>
    </body>
</html>