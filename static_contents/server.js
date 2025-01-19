const express = require('express');
const path = require('path');
const fs = require('fs');
const multer = require('multer'); // For handling file uploads

const app = express();
const initialPort = 7000; // Set your desired initial port number

// Serve static files from the specified directory
app.use('/Images_aiLearn', express.static(path.join(__dirname, 'Images_aiLearn')));

// Handle file uploads using Multer middleware
const upload = multer({
  dest: path.join(__dirname, 'Images_aiLearn', 'uploads')
});

// POST endpoint to handle file uploads
app.post('/upload-image', upload.single('image'), (req, res) => {
  // Multer adds the `file` object to `req`
  const { file } = req;

  if (!file) {
    return res.status(400).json({ error: 'No file uploaded' });
  }

  // Construct the URL for the uploaded file
  const fileUrl = `${req.protocol}://${req.get('host')}/Images_aiLearn/uploads/${file.filename}`;

  // Respond with the URL of the uploaded file
  res.json({ url: fileUrl });
});

// Function to start the server on the next available port
function startServer(port) {
  const server = app.listen(port, () => {
    console.log(`Server is running on http://localhost:${port}`);
  });

  server.on('error', (err) => {
    if (err.code === 'EADDRINUSE') {
      console.log(`Port ${port} is in use, trying port ${port + 1}`);
      startServer(port + 1);
    } else {
      console.error('Server error:', err);
    }
  });
}

// Start the server on the initial port
startServer(initialPort);
