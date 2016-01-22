
#!/bin/bash
echo -e "Checking conditions..\n"
echo "$TRAVIS_REPO_SLUG"
if [ "$TRAVIS_REPO_SLUG" == "stephentuso/welcome-android" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "Publishing javadoc...\n"

  cp -R library/build/docs/javadoc $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/stephentuso/welcome-android gh-pages > /dev/null

  cd gh-pages
  git rm -rf .
  cp -Rf $HOME/javadoc-latest ./
  git add -f .
  git commit -m "Update javadoc (travis build #$TRAVIS_BUILD_NUMBER)"
  git push -fq origin gh-pages > /dev/null

  echo -e "Pushed javadoc to gh-pages.\n"

else
    echo -e "Not publishing javadoc.\n"
fi
