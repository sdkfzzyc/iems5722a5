# to run the script with FastAPI: fastapi dev main.py
# to run the script with uvicorn with fastapi at port 55722: uvicorn main:app --host 0.0.0.0 --port 55722

# import the Fast API package
from fastapi import FastAPI
from datetime import date
from fastapi.responses import JSONResponse
from fastapi.encoders import jsonable_encoder
from pydantic import BaseModel
from fastapi import Request
import json

# for testing, you can update this one to your student ID
student_list = ["1155229623"] 

# define a Fast API app
app = FastAPI()

# define a route, binding a function to a URL (e.g. GET method) of the server
@app.get("/")
async def root():
  return {"message": "Hello World"}  # the API returns a JSON response

mongodb_url = "mongodb+srv://1155229623:<db_password>@cluster0.r0rva.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"
client = pymongo.MongoClient(mongodb_url, tlsCAFile=certifi.where())
db = client.get_default_database()
  
@app.get("/get_chatrooms")
async def get_mongodb_data(request: Request):
    collection = db.get_collection("chatrooms")
    data = list(collection.find())
    return JSONResponse(content={"data": data})

@app.get("/get_messages/")
async def get_chatroom(chatroom_id: int = -1, status_code=200):
    if chatroom_id == 3:
        collection = db.get_collection("chatroom3")
        data = list(collection.find())
        return JSONResponse(content=jsonable_encoder(data))
        
    elif chatroom_id == 2:
        collection = db.get_collection("chatroom2")
        data = list(collection.find())
        return JSONResponse(content=jsonable_encoder(data))

    elif chatroom_id == 1:
        collection = db.get_collection("chatroom1")
        data = list(collection.find())
        return JSONResponse(content=jsonable_encoder(data))

    else:data = {
        "data" : { "messages": [] },
        "status": "ERROR"
    }
    return JSONResponse(content=jsonable_encoder(data))


@app.post("/send_message/")
async def send_message(request: Request):  
    item = await request.json()
    print(request, "\n", item)
    
    list_of_keys = list(item.keys())
    
    if len(list_of_keys) != 4:
        data = {"status": "ERROR"}
        return JSONResponse(content=jsonable_encoder(data))
    
    if "chatroom_id" not in item.keys() or item["chatroom_id"] not in [1, 2, 3, "1", "2", "3"]:
        data = {"status": "ERROR"}
        return JSONResponse(content=jsonable_encoder(data))
    
    if "user_id" not in item.keys() or item["user_id"] not in student_list:
        data = {"status": "ERROR"}
        return JSONResponse(content=jsonable_encoder(data))
        
    if "name" not in item.keys() or len(item["name"]) > 20:
        data = {"status": "ERROR"}
        return JSONResponse(content=jsonable_encoder(data))  

    if "message" not in item.keys() or len(item["message"]) > 20:
        data = {"status": "ERROR"}
        return JSONResponse(content=jsonable_encoder(data)) 
        
    data = {"status": "OK"}
    return JSONResponse(content=jsonable_encoder(data))

