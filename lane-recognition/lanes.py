import cv2
import numpy as np

image = cv2.imread('lane-recognition/images/test_image.jpg')

if image is None:
    print("Could not read the image.")

lane_image = np.copy(image)
gray = cv2.cvtColor(lane_image, cv2.COLOR_RGB2GRAY)
cv2.imshow("Display window", gray)
cv2.waitKey(0)
