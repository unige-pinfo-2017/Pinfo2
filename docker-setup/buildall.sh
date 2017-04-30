for image in ./*-pinfo2 ; do
    cd $image
    docker build -t ${image##*/} . &
    cd ..
done
