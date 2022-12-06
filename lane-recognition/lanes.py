import cv2
import matplotlib.pyplot as plt
import numpy as np


def import_image(filename):
    return cv2.imread(filename)

def canny(image):
    gray = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)
    blurred_image = cv2.GaussianBlur(gray, (5,5), 0)
    return cv2.Canny(blurred_image, 50, 150) # documentacion says 1:3 or 1:2 that's why it's 50 : 150

def display_lines(image, lines):
    line_image = np.zeros_like(image)
    if lines is not None:
        for line in lines:
            x1, y1, x2, y2 = line.reshape(4)
            cv2.line(line_image, (x1, y1), (x2, y2), (0,0,255), 10)
    return line_image
    
def region_of_interest(image):
    image_height = image.shape[0] # gets the number of row which in this case is actully the height of the image
    polygons = np.array([
        [(200,image_height), (1100,image_height), (550,250)]
        ])  #dimensions were gotten by matplotlib analysis of lane pictures # last vertice is the length of the road
    mask = np.zeros_like(image)
    cv2.fillPoly(mask, polygons, 255) #creates a black image that has a white triangle with the predefined values
    masked_image = cv2.bitwise_and(image, mask)
    return masked_image

image = import_image('lane-recognition/images/test_image.jpg')
lane_image = np.copy(image)
canny_image = canny(lane_image)
cropped_image = region_of_interest(canny_image)
lines = cv2.HoughLinesP(cropped_image, 2, np.pi/180, 100, np.array([]), minLineLength=40, maxLineGap=5) #defining rows in pixels and radians to get a grid for Hough transformations and trying to find a bin with the most votes(lines corssing)
line_image = display_lines(lane_image, lines)
cv2.imshow("Image window", line_image)
cv2.waitKey(0)


