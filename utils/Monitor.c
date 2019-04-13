#include <X11/Xlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/time.h>
/****************************************************************
double toMilliseconds(struct timeval time)

Purpose:
    Converts struct timeval to double with milliseconds.

Parameters:
    struct timeval time  timeval structure to convert

Returns:
    Time in milliseconds.
****************************************************************/
double toMilliseconds(struct timeval time){
    return((time.tv_sec*1000) + (time.tv_usec/1000));
}

int main(void) {
    printf("x,y,mouseDown,timestamp\n");
    Display* dpy = XOpenDisplay(0);
    int scr = XDefaultScreen(dpy);
    Window root_window = XRootWindow(dpy, scr);
    Window qRoot;
    Window qChild;
    int qRootX = 0;
    int qRootY = 0;
    int qChildX = 0;
    int qChildY;
    unsigned int qMask;

    int oldQRootX;
    int oldQRootY;
    int oldQMask;
    
    struct timeval stop, start, timestamp;
    gettimeofday(&start, NULL);
    while(1){
	usleep(1000);
	oldQRootX = qRootX;
	oldQRootY = qRootY;
	oldQMask = qMask;
 	XQueryPointer(dpy, root_window, &qRoot, &qChild, &qRootX, &qRootY, &qChildX, &qChildY, &qMask);
	if((oldQRootX != qRootX) ||  (oldQRootY != qRootY) || (oldQMask != qMask)){
	    gettimeofday(&stop, NULL);
            timersub(&stop, &start, &timestamp);
 	    printf("%d,%d,%u,%.f\n", qRootX, qRootY, qMask, toMilliseconds(timestamp));
	}
    }
}
