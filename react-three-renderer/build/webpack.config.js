const webpack = require('webpack')
const path = require('path')

const config = {
  entry: './main.js',
  devtool: 'source-map',
  externals: {
    three: 'three',
    react: 'react',
    'react-dom': 'react-dom'
  },
  output: {
    filename: 'bundle.js'
  }
}

module.exports = config
