// Simple script to generate placeholder avatar images
const fs = require('fs');
const path = require('path');

// Create avatars directory if it doesn't exist
const avatarsDir = 'public/avatars';
if (!fs.existsSync(avatarsDir)){
    fs.mkdirSync(avatarsDir, { recursive: true });
}

// Simple SVG avatar templates
const avatars = [
    `<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128">
  <rect width="128" height="128" fill="#3498db"/>
  <circle cx="64" cy="44" r="20" fill="#f1c40f"/>
  <path d="M44,84 Q64,104 84,84" stroke="#e74c3c" stroke-width="4" fill="none"/>
</svg>`,
    `<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128">
  <rect width="128" height="128" fill="#2ecc71"/>
  <circle cx="64" cy="44" r="20" fill="#9b59b6"/>
  <path d="M44,84 Q64,104 84,84" stroke="#f39c12" stroke-width="4" fill="none"/>
</svg>`,
    `<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128">
  <rect width="128" height="128" fill="#e74c3c"/>
  <circle cx="64" cy="44" r="20" fill="#34495e"/>
  <path d="M44,84 Q64,104 84,84" stroke="#f1c40f" stroke-width="4" fill="none"/>
</svg>`,
    `<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128">
  <rect width="128" height="128" fill="#9b59b6"/>
  <circle cx="64" cy="44" r="20" fill="#1abc9c"/>
  <path d="M44,84 Q64,104 84,84" stroke="#e67e22" stroke-width="4" fill="none"/>
</svg>`,
    `<svg xmlns="http://www.w3.org/2000/svg" width="128" height="128" viewBox="0 0 128 128">
  <rect width="128" height="128" fill="#f39c12"/>
  <circle cx="64" cy="44" r="20" fill="#e74c3c"/>
  <path d="M44,84 Q64,104 84,84" stroke="#2ecc71" stroke-width="4" fill="none"/>
</svg>`
];

// Convert SVG to base64 and save as PNG
const { createCanvas, loadImage } = require('canvas');
const { writeFile } = require('fs').promises;

// Generate avatar images using canvas
async function generateAvatars() {
    const canvas = createCanvas(128, 128);
    const ctx = canvas.getContext('2d');
    
    for (let i = 0; i < avatars.length; i++) {
        // Clear canvas
        ctx.clearRect(0, 0, 128, 128);
        
        // Draw simple colored circle for each avatar
        const colors = ['#3498db', '#2ecc71', '#e74c3c', '#9b59b6', '#f39c12'];
        ctx.fillStyle = colors[i];
        ctx.fillRect(0, 0, 128, 128);
        
        // Draw face features
        ctx.fillStyle = '#f1c40f';
        ctx.beginPath();
        ctx.arc(64, 50, 20, 0, Math.PI * 2); // Face
        ctx.fill();
        
        // Eyes
        ctx.fillStyle = '#2c3e50';
        ctx.beginPath();
        ctx.arc(50, 45, 5, 0, Math.PI * 2); // Left eye
        ctx.arc(78, 45, 5, 0, Math.PI * 2); // Right eye
        ctx.fill();
        
        // Smile
        ctx.strokeStyle = '#2c3e50';
        ctx.lineWidth = 3;
        ctx.beginPath();
        ctx.arc(64, 60, 15, 0, Math.PI); // Smile
        ctx.stroke();
        
        // Save as PNG
        const buffer = canvas.toBuffer('image/png');
        await writeFile(`public/avatars/avatar${i+1}.png`, buffer);
        console.log(`Generated avatar${i+1}.png`);
    }
}

generateAvatars().catch(console.error);