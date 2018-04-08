# /usr/bin/python2.7
from os import abort

import flask
import psycopg2
from flask import g, json, request

app = flask.Flask(__name__)
app.config.update(
    DATABASE={
        'dbname': 'hotelki',
        'username': 'postgres',
        'password': '13524',
        'host': '77.108.109.83',
        'port': 30030
    }
)


def connect_db():
    conn = None
    try:
        conn = psycopg2.connect(dbname='hotelki', user='postgres', password='13524', host='77.108.109.83', port='30030')
    except:
        print("Cant connect to db")
    return conn


def get_db():
    if not hasattr(g, 'postgre_db'):
        g.postgre_db = connect_db()
    return g.postgre_db


# def init_db():
#     db = get_db()
#     with app.open_resource('schema.sql', mode='r') as f:
#         db.cursor().execurescript(f.read())
#     db.commit()


# @app.cli.command('initdb')
# def initdb_cmd():
#     init_db()
#     print("Initialized db")
#
#
# @app.cli.command('dropdb')
# def dropdb_cmd():
#     drop_db()
#     print("Dropped db")


# @app.teardown_appcontext
# def close_db(error):
# 	if hasattr(g, 'postgre_db'):
# 		g.postgre_db.close()

@app.route('/')
def show():
	comments = [{'author': 'John Doe', 'content': 'Lorem ipsum'}, {'author': 'Mark', 'content': 'Я люблю кофер'}]
	stories = [{'id': 1, 'title': 'a', 'likes': 15, 'dislikes': 11, 'description': 'lorem ipsum ist dalor'}, 
						{'id': 2, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогие друзья'}, 
						{'id': 3, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогой друщь'}, 
						{'id': 4, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Друсь'}, 
						{'id': 5, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогие друзья'}, 
						{'id': 6, 'title': 'b', 'likes': 3, 'dislikes': 4, 'description': 'Дорогие друзья'}
						]
	return flask.render_template('index.html', stories=stories, comments=comments)


@app.route('/api/get/markers', methods=['GET'])
def get_task1():
    rows = get_all_markers()
    markers = markers_rows_to_dicts(rows)
    json_str = json.dumps(markers)
    return flask.jsonify(json_str)


@app.route('/api/get/markers/<id>', methods=['GET'])
def get_task2(id):
    rows = get_marker_by_id(id)
    markers = markers_rows_to_dicts(rows)
    json_str = json.dumps(markers)
    return flask.jsonify(json_str)


@app.route('/api/get/comments/<marker_id>', methods=['GET'])
def get_task3(marker_id):
    rows = get_all_comments(marker_id)
    comments = comments_rows_to_dicts(rows)
    json_str = json.dumps(comments)
    return flask.jsonify(json_str)


@app.route('/api/get/marker/non_approve', methods=['GET'])
def get_task4():
    rows = get_nonapproved_markers()
    comments = comments_rows_to_dicts(rows)
    json_str = json.dumps(comments)
    return flask.jsonify(json_str)


@app.route('/api/get/marker/non_approve', methods=['GET'])
def get_task5():
    rows = get_nonapproved_markers()
    comments = comments_rows_to_dicts(rows)
    json_str = json.dumps(comments)
    return flask.jsonify(json_str)


@app.route('/api/get/marker/create', methods=['POST'])
def create_marker():
    data = request.json2
    if not data:
        abort(400)
    marker_dict = json.load(data)
    create_marker(marker_dict)
    return 201


@app.route('/api/get/comments/create', methods=['POST'])
def create_comment():
    data = request.json
    if not data:
        abort(400)
    comments_dict = json.load(data)
    create_comments(comments_dict)
    return 201


@app.route('/api/get/markers/like/<id>', methods=['POST'])
def marker_like(id):
    make_like(id)
    return 201


@app.route('/api/get/markers/dislike/<id>', methods=['POST'])
def marker_dislike(id):
    make_dislike(id)
    return 201


@app.route('/api/get/markers/gov_feedback/<id>&<approved_level>&<message>', methods=['POST'])
def update_marker(id, approved_level, message):
    update_approvement(id, message, approved_level)
    return 201


def comments_rows_to_dicts(rows):
    markers = list()
    for row in rows:
        markers.append({'id': row[1], 'author': row[2], 'marker': row[3], 'message': row[4]})
    return markers


def markers_rows_to_dicts(rows):
    markers = list()
    for row in rows:
        markers.append({'id': row[1], 'name': row[2], 'lan': row[3], 'lon': row[4], 'description': row[5]
                           , 'likes': row[6], 'dislikes': row[7], 'tags': row[8], 'approved': row[8],
                        'comments': row[9], 'author': row[10], 'comment_from_gov': row[11]})
    return markers


def get_all_markers():
    cursor = get_db().cursor()
    cursor.execute("SELECT * FROM markers;")
    rows = cursor.fetchall()
    return rows


def get_marker_by_id(ident):
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM markers WHERE id='+str(ident)+';')
    rows = cursor.fetchall()
    return rows


def get_all_comments(marker_id):
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM comments WHERE marker=\''+str(marker_id)+'\';')
    rows = cursor.fetchall()
    return rows


def get_nonapproved_markers():
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM markers WHERE approved=0;') #approved=0 means that it is not seen by governments
    rows = cursor.fetchall()
    return rows


def create_marker(marker):
    cursor = get_db().cursor()
    marker_list = marker.values()
    str_marker_list = list()
    for i in marker_list:
        str_marker_list.append(str(i)+',')
    str_marker_list[-1][-1] = ''
    values = str()
    for i in str_marker_list:
        values = values+i
    cursor.execute('INSERT INTO markers (id,name,lan,lon,description,likes'
                   ',dislikes,tags,approved,author,comments_from_government) VALUES ('
                   + values + ' );')


def create_comments(comment):
    cursor = get_db().cursor()
    marker_list = comment.values()
    str_comment_list = list()
    for i in marker_list:
        str_comment_list.append(str(i)+',')
    str_comment_list[-1][-1] = ''
    values = str()
    for i in str_comment_list:
        values = values+i
    cursor.execute('INSERT INTO comments (Id,author,message,marker) VALUES ('
                   + values + ' );')


def make_like(id):
    cursor = get_db().cursor()
    cursor.execute("UPDATE markers SET likes = (SELECT likes FROM markers WHERE id =\'"+str(id)+'\')+1'
                   + 'WHERE id =\''+str(id)+'\';')


def make_dislike(id):
    cursor = get_db().cursor()
    cursor.execute("UPDATE markers SET dislikes = (SELECT dislikes FROM markers WHERE id =\'"+str(id)+'\')+1'
                   + 'WHERE id =\''+str(id)+'\';')


def update_approvement(id, message, approvement_level):
    cursor = get_db().cursor()
    cursor.execute('UPDATE markers SET approved = '+str(approvement_level) + ', comment_from_governments = ' +
                   str(message) + 'WHERE id =\''+str(id)+'\';')