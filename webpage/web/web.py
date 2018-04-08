# /usr/bin/python2.7

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


# Works
@app.route('/api/get/markers', methods=['GET'])
def get_request_all_markers():
    rows = get_all_markers()
    markers = markers_rows_to_dicts(rows)
    json_str = json.dumps(markers)
    print(json_str)
    return json_str


# WOrks
@app.route('/api/get/markers/', methods=['GET'])
def get_request_marker_by_id():
    print("i am alive")
    data = request.get_json()
    print("i am alive2")
    print(json)
    item_id = request.args['id']
    print(item_id)
    rows = get_marker_by_id(item_id)
    markers = markers_rows_to_dicts(rows)
    json_str = json.dumps(markers)
    return json_str


@app.route('/api/get/comments/', methods=['GET'])
def get_request_comments_by_id():
    # data = request.get_json()
    marker_id = request.args['id']
    rows = get_all_comments(marker_id)
    comments = comments_rows_to_dicts(rows)
    json_str = json.dumps(comments)
    return json_str


@app.route('/api/get/markers_by_points/', methods=['GET'])
def get_request_markers_by_points():
    data = request.get_json()
    lan = request.args['lan']
    lon = request.args['lon']
    rows = get_markers_by_points(float(lan), float(lon))
    comments = comments_rows_to_dicts(rows)
    json_str = json.dumps(comments)
    return json_str


@app.route('/api/get/markers/non_approve', methods=['GET'])
def get_request_nonapproveed_markers():
    rows = get_nonapproved_markers()
    comments = comments_rows_to_dicts(rows)
    json_str = json.dumps(comments)
    return json_str


@app.route('/api/post/marker/create', methods=['POST'])
def create_marker():
    data = request.get_json()
    marker_dict = json.load(data)
    create_marker(marker_dict)
    return 201


@app.route('/api/post/comments/create', methods=['POST'])
def create_comment():
    data = request.get_json()
    comments_dict = json.load(data)
    create_comments(comments_dict)
    return 201


@app.route('/api/post/markers/like', methods=['POST'])
def marker_like():
    data = request.get_json()
    item_id = request.args['id']
    make_like(item_id)
    return 201


@app.route('/api/post/markers/dislike', methods=['POST'])
def marker_dislike():
    data = request.get_json()
    item_id = request.args['id']
    make_dislike(item_id)
    return 201


@app.route('/api/post/markers/gov_feedback', methods=['POST'])
def update_marker():
    print("Accessed")
    data = request.get_json()
    item_id = request.args['id']
    approved_level = request.args['approved_level']
    message = request.args['message']
    print("{} & {} & {}".format(item_id, approved_level, message))
    update_approvement(item_id, message, approved_level)
    return 201


def comments_rows_to_dicts(rows):
    markers = list()
    for row in rows:
        markers.append({'id': row[0], 'author': row[1], 'marker': row[2], 'message': row[3]})
    return markers


def markers_rows_to_dicts(rows):
    markers = list()
    for row in rows:
        print(rows)
        markers.append({'id': row[0], 'name': row[1], 'lan': row[2], 'lon': row[3], 'description': row[4]
                           , 'likes': row[5], 'dislikes': row[6], 'tags': row[7], 'approved': row[8]
                           , 'comment_from_gov': row[9], 'author': row[10]})
    return markers


def get_all_markers():
    cursor = get_db().cursor()
    cursor.execute("SELECT * FROM markers;")
    rows = cursor.fetchall()
    return rows


def get_marker_by_id(ident):
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM markers WHERE id=' + str(ident) + ';')
    rows = cursor.fetchall()
    return rows


def get_all_comments(marker_id):
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM comments WHERE marker=' + str(marker_id) + ';')
    rows = cursor.fetchall()
    return rows


def get_nonapproved_markers():
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM markers WHERE approved=0;')  # approved=0 means that it is not seen by governments
    rows = cursor.fetchall()
    return rows


def create_marker(marker):
    cursor = get_db().cursor()
    marker_list = marker.values()
    # str_marker_list = list()
    # for i in marker_list:
    #     str_marker_list.append(str(i) + ',')
    # str_marker_list[-1][-1] = ''
    # values = str()
    # for i in str_marker_list:
    #     values = values + i
    cursor.execute("INSERT INTO markers (name,lan,lon,description,likes"
                   ",dislikes,tags,approved,comments_from_government,author) VALUES ('{}',{},{},'{}',{},{},'{}',{},'{}','{}' );"
                   .format(marker_list[0], marker_list[1], marker_list[2], marker_list[3], marker_list[4],
                           marker_list[5], marker_list[6], marker_list[7], marker_list[8], marker_list[9]))


def create_comments(comment):
    cursor = get_db().cursor()
    marker_list = comment.values()
    # str_comment_list = list()
    # for i in marker_list:
    #     str_comment_list.append(str(i) + ',')
    # str_comment_list[-1][-1] = ''
    # values = str()
    # for i in marker_list:
    #     values = values + i
    cursor.execute("INSERT INTO comments (author,message,marker) VALUES ('{}','{}',{} );"
                   .format(marker_list[0], marker_list[0 + 1], marker_list[0 + 2]))


def make_like(item_id):
    cursor = get_db().cursor()
    cursor.execute("UPDATE markers SET likes = (SELECT likes FROM markers WHERE id =\'" + str(item_id) + '\')+1'
                   + 'WHERE id =\'' + str(id) + '\';')


def make_dislike(item_id):
    cursor = get_db().cursor()
    cursor.execute("UPDATE markers SET dislikes = (SELECT dislikes FROM markers WHERE id =\'" + str(item_id) + '\')+1'
                   + 'WHERE id =\'' + str(id) + '\';')


def update_approvement(item_id, message, approvement_level):
    cursor = get_db().cursor()
    cursor.execute('UPDATE markers SET approved = ' + str(approvement_level) + ', comment_from_governments = ' +
                   str(message) + 'WHERE id =\'' + str(item_id) + '\';')


def get_markers_by_points(lan, lon):
    cursor = get_db().cursor()
    cursor.execute('SELECT * FROM markers WHERE lan =' + str(lan) + ' AND lon = ' + str(lon) + ';')
    rows = cursor.fetchall()
    return rows
