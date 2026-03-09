/**
 * 前端安全配置指南
 * 生产环境部署时必须遵循以下规范
 */

// ============= Web 服务器安全配置示例 =============

// === Nginx 配置 ===
/*
server {
    listen 80;
    server_name yourdomain.com;

    root /var/www/attendance-system/frontend/dist;
    index index.html;

    # 防止源代码暴露 - 禁用目录列表
    autoindex off;

    # 安全响应头
    add_header X-Content-Type-Options "nosniff" always;
    add_header X-Frame-Options "SAMEORIGIN" always;
    add_header X-XSS-Protection "1; mode=block" always;
    add_header Referrer-Policy "strict-origin-when-cross-origin" always;
    add_header Permissions-Policy "geolocation=(), microphone=(), camera=()" always;

    # 禁止访问源代码相关文件
    location ~ /\. {
        deny all;
        access_log off;
        log_not_found off;
    }

    location ~ ~$ {
        deny all;
        access_log off;
        log_not_found off;
    }

    # 代理 API 请求到后端
    location /api/ {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }

    # Vue Router 配置 - 所有路由回到 index.html
    location / {
        try_files $uri $uri/ /index.html;
    }

    # 缓存静态资源（带 hash 的文件可以长期缓存）
    location ~* \.(js|css|png|jpg|jpeg|gif|ico|svg|woff|woff2|ttf|eot)$ {
        expires 1y;
        add_header Cache-Control "public, immutable";
    }
}
*/

// === Apache 配置 ===
/*
<VirtualHost *:80>
    ServerName yourdomain.com
    DocumentRoot /var/www/attendance-system/frontend/dist

    # 防止目录列表
    <Directory /var/www/attendance-system/frontend/dist>
        Options -Indexes
        AllowOverride All
        Require all granted

        # 路由回到 index.html
        <IfModule mod_rewrite.c>
            RewriteEngine On
            RewriteBase /
            RewriteRule ^index\.html$ - [L]
            RewriteCond %{REQUEST_FILENAME} !-f
            RewriteCond %{REQUEST_FILENAME} !-d
            RewriteRule . /index.html [L]
        </IfModule>
    </Directory>

    # 安全响应头
    Header always set X-Content-Type-Options "nosniff"
    Header always set X-Frame-Options "SAMEORIGIN"
    Header always set X-XSS-Protection "1; mode=block"

    # 禁止访问隐藏文件和 backup 文件
    <FilesMatch "^\.|~$">
        Require all denied
    </FilesMatch>

    # API 代理
    ProxyPreserveHost On
    ProxyPass /api/ http://localhost:8080/api/
    ProxyPassReverse /api/ http://localhost:8080/api/
</VirtualHost>
*/

// ============= 前端代码规范 =============

// ✅ 已实施：
// 1. 生产构建禁用 source map - vite.config.ts 中 sourcemap: false
// 2. 代码压缩和混淆 - terser 配置，移除 console 和注释
// 3. 文件名使用 hash - 防止猜测文件名
// 4. API 请求通过代理 - 隐藏真实后端地址

// ⚠️ 需要注意：
// 1. 环境变量使用 .env 文件，不要硬编码敏感信息
// 2. 认证 token 存储在 HTTP-only Cookie 而非 localStorage
// 3. 定期检查浏览器控制台是否有敏感日志输出
// 4. 生产环境使用 HTTPS，不要用 HTTP
// 5. 设置合理的 CORS 策略，只允许来自自己服务的请求
