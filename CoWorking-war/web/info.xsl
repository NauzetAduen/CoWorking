<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet 
    xmlns:xsl=
    "http://www.w3.org/1999/XSL/Transform" 
    version="1.0"
>

    <xsl:template match="info">
        <HTML>
            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
                <title>Gestor Aulas ULPGC</title>
                <link rel="stylesheet" type="text/css" href="main.css" /> 
        </head>
            <BODY>
                <h1> TransformView Example </h1>
                <div id="TransViewPanel">
                    <xsl:apply-templates/>
                </div>
                    
            </BODY>
        </HTML>
    </xsl:template>

    <xsl:template match="description">
        <h2>Description: </h2>
        <p>
           <xsl:apply-templates/>
        </p>
    </xsl:template>
    <xsl:template match="developer">
        <h2>Developer: </h2>
        <p>
           <xsl:apply-templates/>
        </p>
    </xsl:template>

    <xsl:template match="application">
        <h2>Aplication: </h2>
        <ul>
        <xsl:apply-templates/>
        </ul>
    </xsl:template>
    <xsl:template match="name">
        <li>Name: 
        <xsl:apply-templates/>
        </li>
    </xsl:template>
    <xsl:template match="year">
        <li>Year:  
        <xsl:apply-templates/>
        </li>
    </xsl:template>

    <xsl:template match="languages">
        <li>Languages:
            <ul>
            <xsl:apply-templates/>
            </ul>
        </li>
    </xsl:template>

    <xsl:template match="language">
        <li><xsl:apply-templates/>
        </li>
    </xsl:template>

    <xsl:output method="html"/>

</xsl:stylesheet>