# Android Wear Color Picker

This is a simple color picker app for Android Wear and round Smartwatches.
It has been tested with a Motorola Moto 360.

A ring with a color gradient is aligned to the bezel of the Smartwatch. The 
ring can be dragged by touch gestures, the active color being the one at 12 
o'clock. The active color is always shown in the inner part of the ring.

The code from this app could be included into an app as a mean to enable 
color choices (e.g. by tapping the inner area of the ring, see line 46 in
`RingController.java`). This app originated from a much larger app and some
of the code could probably still be stripped down.

## How it looks

![Android Wear Color Picker](https://github.com/cmichi/android-wear-color-picker/raw/master/colorpicker.jpg)

The black bar at the bottom of the watch is part of every Moto 360. This
"flat tire" contains hardware sensors. On Smartwatches without this
shortcoming (such as the LG R) the ring is displayed fully.

## License

The code is licensed under the MIT license.

	Copyright (c) 2015

		Michael Mueller <http://micha.elmueller.net/>

	Permission is hereby granted, free of charge, to any person obtaining
	a copy of this software and associated documentation files (the
	"Software"), to deal in the Software without restriction, including
	without limitation the rights to use, copy, modify, merge, publish,
	distribute, sublicense, and/or sell copies of the Software, and to
	permit persons to whom the Software is furnished to do so, subject to
	the following conditions:

	The above copyright notice and this permission notice shall be
	included in all copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
	EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
	MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
	NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
	LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
	OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
	WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
