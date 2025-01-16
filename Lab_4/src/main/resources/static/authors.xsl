<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/authors">
        <html>
            <head>
                <title>Authors</title>
            </head>
            <body>
                <h2>Authors List</h2>
                <table border="1">
                    <tr>
                        <th>ID</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Email</th>
                    </tr>
                    <xsl:for-each select="author">
                        <tr>
                            <td><xsl:value-of select="id" /></td>
                            <td><xsl:value-of select="firstName" /></td>
                            <td><xsl:value-of select="lastName" /></td>
                            <td><xsl:value-of select="email" /></td>
                        </tr>
                    </xsl:for-each>
                </table>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
