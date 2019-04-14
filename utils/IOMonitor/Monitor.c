#include <X11/Xlib.h>
#include <X11/keysymdef.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/time.h>
#include <linux/input.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>



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
    printf("x,y,mouseDown,keyDown,timestamp\n");
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
    int fd;
    int oldQRootX;
    int oldQRootY;
    int oldQMask;
    int oldMili;
    struct timeval stop, start, timestamp;
    gettimeofday(&start, NULL);
    
    unsigned long event_mask;
    event_mask = KeyReleaseMask | ButtonReleaseMask; 
    int state;
    
    fd = open("/dev/input/event4", O_RDONLY);
    struct input_event ev;

    int flags = fcntl(fd, F_GETFL, 0);
    fcntl(fd, F_SETFL, flags | O_NONBLOCK);

    short isKeyPressed = 0;
    while(1){
	usleep(1000);
	oldQRootX = qRootX;
	oldQRootY = qRootY;
	oldQMask = qMask;
 	XQueryPointer(dpy, root_window, &qRoot, &qChild, &qRootX, &qRootY, &qChildX, &qChildY, &qMask);
	if((oldQRootX != qRootX) ||  (oldQRootY != qRootY) || (oldQMask != qMask)){
	    gettimeofday(&stop, NULL);
            timersub(&stop, &start, &timestamp);
 	    printf("%d,%d,%u,0,%.f\n", qRootX, qRootY, qMask, toMilliseconds(timestamp));
        }
            gettimeofday(&stop, NULL);
            timersub(&stop, &start, &timestamp);
	    read(fd, &ev, sizeof(struct input_event));
            if(ev.type == 4 && isKeyPressed == 0){
                printf("%d,%d,%u,1,%.f\n", qRootX, qRootY, qMask, toMilliseconds(timestamp));
                isKeyPressed = 1;
	    }else if(ev.type == 4 && isKeyPressed == 1){
                 isKeyPressed = 0;
 	    }
    }
}
