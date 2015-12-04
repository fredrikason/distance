Description: This is the solution for Problem 2 - Distance (see below for complete results)
The solution consists of two domain objects; Point and PointInputStream (and corresponding junit test cases) 
and one java application "numbers.distance.PointCalculator" (see below for how to run the main java application)
The source code is an eclipse maven project which can be built using maven (there is a single dependency on junit 4.8.2) 

Usage: [-f] <file> [-n] <number of points> [-m] <mode: c/f (closest/farthest)> [-x] <x number ref point> [-y] <y number ref point> [-h]

	file: file path to the points binary file
	number of points: this is the number of points to calculate, e.g. 10 or 20 etc.
	mode: mode of calculations, either c (closest) or f (farthest)
	x number of point: this is the x coordinate for the reference point
	y number of point: this is the y coordinate for the reference point

Examples: -f "C:/temp/Problem 2 - Distance/points" -n 10 -m c -x -200 -y 300
		  -f "C:/temp/Problem 2 - Distance/points" -n 20 -m f -x 1000 -y 25

Results:

Calculating the 10 CLOSEST points to Point (-200, 300)
Point (-198, 292) with distance d=8.246211251235321
Point (-194, 307) with distance d=9.219544457292887
Point (-191, 285) with distance d=17.4928556845359
Point (-191, 319) with distance d=21.02379604162864
Point (-220, 289) with distance d=22.825424421026653
Point (-224, 317) with distance d=29.410882339705484
Point (-170, 292) with distance d=31.04834939252005
Point (-232, 311) with distance d=33.83784863137726
Point (-190, 334) with distance d=35.4400902933387
Point (-194, 265) with distance d=35.510561809129406
10000000 points processed in 1988 ms

Calculating the 20 farthest points to Point (1000, 25)
Point (-32761, -32735) with distance d=47042.775438955556
Point (-32764, -32731) with distance d=47042.14314845785
Point (-32724, -32759) with distance d=47032.954744519295
Point (-32764, -32706) with distance d=47024.7387765206
Point (-32761, 32751) with distance d=47019.10459589804
Point (-32766, 32739) with distance d=47014.34410900571
Point (-32743, -32710) with distance d=47012.44807495138
Point (-32715, -32727) with distance d=47004.19905710553
Point (-32745, 32745) with distance d=47003.4405655586
Point (-32746, -32691) with distance d=47001.37415012459
Point (-32671, -32767) with distance d=47000.5479223381
Point (-32722, -32712) with distance d=46998.77076052096
Point (-32711, -32723) with distance d=46998.54279655913
Point (-32764, -32659) with distance d=46992.03711268538
Point (-32751, -32672) with distance d=46991.7419340888
Point (-32766, 32704) with distance d=46989.996775909654
Point (-32719, -32697) with distance d=46986.17078460427
Point (-32732, 32731) with distance d=46984.361866476385
Point (-32729, 32734) with distance d=46984.29654682509
Point (-32681, -32731) with distance d=46982.60632404294
10000000 points processed in 1981 ms