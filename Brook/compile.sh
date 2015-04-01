echo "Begin"
echo "copy GWT.xml file"
root="/Users/truongnguyen/Documents/workspace"
project="$root/Cordova/Cordova-Brook"

gwtProject="$root/Brook"
ant -f build.xml

echo "removing previous compiled folder"
rm -rf $project/www/brook/*
rm -rf $project/www/css/*
rm -rf $project/www/images/*

rm -rf $project/platforms/ios/www/brook/*
rm -rf $project/platforms/ios/www/css/*
rm -rf $project/platforms/ios/www/images/*

rm -rf $project/platforms/android/assets/www/brook/*
rm -rf $project/platforms/android/assets/www/css/*
rm -rf $project/platforms/android/assets/www/images/*

echo "copying newly compiled folder"
cp -r $gwtProject/war/brook/* $project/www/brook/
cp -r $gwtProject/war/css/* $project/www/css/
cp -r $gwtProject/war/images/* $project/www/images/

cp -r $gwtProject/war/brook/* $project/platforms/ios/www/brook/
cp -r $gwtProject/war/css/* $project/platforms/ios/www/css/
cp -r $gwtProject/war/images/* $project/platforms/ios/www/images/

cp -r $gwtProject/war/brook/* $project/platforms/android/assets/www/brook/
cp -r $gwtProject/war/css/* $project/platforms/android/assets/www/css/
cp -r $gwtProject/war/images/* $project/platforms/android/assets/www/images/

ant -f $project/platforms/android/build.xml clean
ant -f $project/platforms/android/build.xml release

adb uninstall com.fut.brook
adb install $project/platforms/android/bin/brook-release.apk

echo "Done! enjoy result ^^"
