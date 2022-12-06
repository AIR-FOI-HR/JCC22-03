import cv2
import numpy as np

image = cv2.imread('lane-recognition/images/test_image.jpg')

if image is None:
    print("Could not read the image.")

lane_image = np.copy(image)
gray = cv2.cvtColor(lane_image, cv2.COLOR_RGB2GRAY)

blurred_image = cv2.GaussianBlur(gray, (5,5), 0)
canny = cv2.Canny(blurred_image, 50, 150) # documentacion says 1:3
cv2.imshow("Display window", canny)
cv2.waitKey(0)

