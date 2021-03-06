// Karma configuration
// Generated on Thu Aug 29 2013 11:55:41 GMT+0200 (Romance Daylight Time)

module.exports = function(config) {
  config.set({

    // base path, that will be used to resolve files and exclude
    basePath: '../../../../../',


    // frameworks to use
    frameworks: ['jasmine'],


    // list of files / patterns to load in the browser
    files: [
        {pattern: 'src/main/webapp/**/*.html', included: false},
        'src/main/webapp/resources/js/lib/jquery-2.1.1.min.js',
        'src/main/webapp/resources/js/lib/angular.min.js',
        'src/main/webapp/resources/js/lib/ui-bootstrap-tpls-0.10.0.min.js',
        'src/main/webapp/resources/js/lib/angular-translate.min.js',
        'src/main/webapp/resources/js/**/*.js',
        'src/test/js/karma/lib/**/*.js',
        'src/test/js/unit/**/*.js'
    ],


    // list of files to exclude
    exclude: [
      
    ],


    // test results reporter to use
    // possible values: 'dots', 'progress', 'junit', 'growl', 'coverage'
    reporters: ['progress'],
    junitReporter: {
      outputFile: 'target/surefire-reports/TEST-karma.xml',
      suite:'unit'
    },

    // web server port
    port: 9876,


    // enable / disable colors in the output (reporters and logs)
    colors: true,


    // level of logging
    // possible values: config.LOG_DISABLE || config.LOG_ERROR || config.LOG_WARN || config.LOG_INFO || config.LOG_DEBUG
    logLevel: config.LOG_INFO,


    // enable / disable watching file and executing tests whenever any file changes
    autoWatch: true,


    // Start these browsers, currently available:
    // - Chrome
    // - ChromeCanary
    // - Firefox
    // - Opera
    // - Safari (only Mac)
    // - PhantomJS
    // - IE (only Windows)
    browsers: ['Chrome'],


    // If browser does not capture in given timeout [ms], kill it
    captureTimeout: 60000,


    // Continuous Integration mode
    // if true, it capture browsers, run tests and exit
    singleRun: false
  });
};
