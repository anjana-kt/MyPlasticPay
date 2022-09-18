import cv2
import numpy as np
import time
import qrcode

thres = 0.45 
nms_threshold = 0.2
cap = cv2.VideoCapture(0)

classNames= []
classFile = 'data/coco.names'
obj_qr = qrcode.QRCode(  
    version = 1,  
    error_correction = qrcode.constants.ERROR_CORRECT_L,  
    box_size = 10,  
    border = 4,  
)  

link = "aZhbk30"

def getQRCode():
    obj_qr.add_data(link)  
    obj_qr.make(fit = True)   
    qr_img = obj_qr.make_image(fill_color = "white", back_color = "black")  
    qr_img.save("qr-img.png")   

def display_qrcode():
    img = cv2.imread('qr-img.png')
    cv2.imshow('QR code',img)
    cv2.waitKey(0)

    cv2.destroyAllWindows()

with open(classFile,'rt') as f:
    classNames = f.read().rstrip('\n').split('\n')


configPath = 'data/ssd_mobilenet_v3_large_coco_2020_01_14.pbtxt'
weightsPath = 'data/frozen_inference_graph.pb'

net = cv2.dnn_DetectionModel(weightsPath,configPath)
net.setInputSize(320,320)
net.setInputScale(1.0/ 127.5)
net.setInputMean((127.5, 127.5, 127.5))
net.setInputSwapRB(True)
font = cv2.FONT_HERSHEY_SIMPLEX
while True:
    success,img = cap.read()

    classIds, confs, bbox = net.detect(img,confThreshold=thres)
    bbox = list(bbox)
    confs = list(np.array(confs).reshape(1,-1)[0])
    confs = list(map(float,confs))

    
    if classIds[[0]] in [77,90]:
        print("Please keep your mobile phone away during detection...")
        cv2.putText(img, 
                'Keep your phone away', 
                (50, 50), 
                font, 1, 
                (0, 255, 255), 
                2, 
                cv2.LINE_4)
        # time.sleep(2)

    elif [77,90] not in classIds and classIds[[0]] in [44,90]:
        print("Bottle detected! \nOPENING THE VENT NOW....")
 
        time.sleep(1.5)
        cap.release()
        cv2.destroyAllWindows()

        getQRCode()
        display_qrcode()

        cv2.destroyAllWindows()
        break

    k = cv2.waitKey(1)
    if k%256 == 27:
        print("Escape hit, closing...")
        break
        cv2.destroyAllWindows()

    cv2.imshow("Output",img)
    cv2.waitKey(1)

cv2.destroyAllWindows()
