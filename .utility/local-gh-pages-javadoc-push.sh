#!/bin/bash
echo "Publishing javadoc..."

WORKINGDIR="$HOME/.gh-pages-javadoc-push"

BUILDDIR="$1"
if [ $# -eq 0 ]
  then
    BUILDDIR="./library/build"
fi

mkdir $WORKINGDIR

cp -Rf $BUILDDIR/docs/javadoc $WORKINGDIR/javadoc-latest

cd $WORKINGDIR

echo "Cloning gh-pages..."
git clone --quiet --branch=gh-pages https://$GH_TOKEN@github.com/stephentuso/welcome-android gh-pages > /dev/null

echo "Replacing with updated javadoc..."
cd gh-pages
git rm -rf ./javadoc
cp -Rf $WORKINGDIR/javadoc-latest/ ./javadoc
git add -f .
git commit -m "Update javadoc"

echo "Pushing..."
git push -fq origin gh-pages > /dev/null

cd $HOME
rm -rf $WORKINGDIR

echo "Pushed javadoc to gh-pages."
