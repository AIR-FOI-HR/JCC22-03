import cv2
import matplotlib.pyplot as plt
import numpy as np


def make_cordinates(image, line_parameters):
    slope, intercept = line_parameters
    y1 = image.shape[0] #bottom left corner of image necause the max height is at the bottom of the image
    y2 = int(y1 * (3 / 5)) # go upword 3/5 of the image
    x1 = int((y1 - intercept) / slope) #y = mx + b therefore x = (y - b) / m
    x2 = int((y2 - intercept) / slope)
    return np.array([x1, y1, x2, y2])
    
def average_slope_intercept(image, lines):
    left_fit = []
    right_fit = []
    for line in lines:
        x1, y1, x2, y2 = line.reshape(4)
        parameters = np.polyfit((x1, x2), (y1, y2), 1) #we get the slope and y intercept (prijevod=nagib) for a linear function y=mx + b so we get the x  and b
        slope = parameters[0]
        intercept = parameters[1]
        if slope < 0: #if slope is negative then it's the left line, otherwise it's the right
            left_fit.append((slope, intercept))
        else:
            right_fit.append((slope, intercept))
            
    left_fit_average = np.average(left_fit, axis=0)
    right_fit_average = np.average(right_fit, axis=0)
    left_line = make_cordinates(image, left_fit_average)
    right_line = make_cordinates(image, right_fit_average)
    return np.array([left_line, right_line])
    
def import_image(filename):
    return cv2.imread(filename)

def canny(image):
    gray = cv2.cvtColor(image, cv2.COLOR_RGB2GRAY)
    blurred_image = cv2.GaussianBlur(gray, (5,5), 0)
    return cv2.Canny(blurred_image, 50, 150) # documentacion says 1:3 or 1:2 that's why it's 50 : 150

def display_lines(image, lines):
    line_image = np.zeros_like(image)
    if lines is not None:
        for x1, y1, x2, y2 in lines:
            cv2.line(line_image, (x1, y1), (x2, y2), (0,255,0), 10)
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

def output_image_with_lanes(lane_image, isImage):
    canny_image = canny(lane_image)
    cropped_image = region_of_interest(canny_image)
    lines = cv2.HoughLinesP(cropped_image, 2, np.pi/180, 100, np.array([]), minLineLength=40, maxLineGap=5) #defining rows in pixels and radians to get a grid for Hough transformations and trying to find a bin with the most votes(lines corssing)
    averaged_lines = average_slope_intercept(lane_image, lines)
    line_image = display_lines(lane_image, averaged_lines)
    output_image = cv2.addWeighted(lane_image, 0.8, line_image, 1, 1) #makes lane_image a bit darker -- 0.8 so 20% darker to better define lines
    cv2.imshow("Image window", output_image)
    if isImage:
        cv2.waitKey(0)
    else:
        cv2.waitKey(1)
    
image = import_image('lane-recognition/materials/test_image.jpg')
lane_image = np.copy(image)
#output_image_with_lanes(lane_image,True)
cap = cv2.VideoCapture("lane-recognition/materials/test2.mp4")

while (cap.isOpened()):
    _, frame = cap.read()
    output_image_with_lanes(frame, False)

