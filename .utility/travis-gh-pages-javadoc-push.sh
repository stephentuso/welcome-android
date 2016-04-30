
#!/bin/bash
echo -e "\nChecking conditions.."
if [ "$TRAVIS_REPO_SLUG" == "stephentuso/welcome-android" ] && [ "$TRAVIS_JDK_VERSION" == "oraclejdk8" ] && [ "$TRAVIS_PULL_REQUEST" == "false" ] && [ "$TRAVIS_BRANCH" == "master" ]; then

  echo -e "\nPublishing javadoc..."

  cp -R library/build/docs/javadoc $HOME/javadoc-latest

  cd $HOME
  git config --global user.email "travis@travis-ci.org"
  git config --global user.name "travis-ci"

  echo -e "\nCloning gh-pages..."
  git clone --quiet --branch=gh-pages https://${GH_TOKEN}@github.com/stephentuso/welcome-android gh-pages > /dev/null

  echo -e "\nReplacing with updated javadoc..."
  cd gh-pages
  git rm -rf ./javadoc
  cp -Rf $HOME/javadoc-latest/ ./javadoc
  git add -f .
  git commit -m "Update javadoc (travis build #$TRAVIS_BUILD_NUMBER)"

  echo -e "\nPushing..."
  git push -fq origin gh-pages > /dev/null

  echo -e "\nPushed javadoc to gh-pages."

else
    echo -e "Not publishing javadoc.\n"
fi
