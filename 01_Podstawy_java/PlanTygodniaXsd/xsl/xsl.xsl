<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
  xmlns:tns="http://www.example.org/timetable"
  exclude-result-prefixes="tns">

  <xsl:output method="html" indent="yes" />

  <xsl:template match="/">
    <html>
      <head>
        <title>Plan Lekcji</title>
        <style>
          body { font-family:Comic Sans MS; margin: 25px; }
          table { border-collapse: collapse; width: 75%; }
          th, td { border: 0.5px solid #ccc; padding: 10px; text-align: left; }
          th { background-color: #76AE31; }
          .break { background-color: #ffe0e0; }
          .substitute { background-color: #e0f0ff; }
          .free { background-color: #f0f0e0; }
        </style>
      </head>
      <body>
        <h1>Plan Lekcji</h1>
        <xsl:apply-templates select="tns:schedule/tns:dayofTheweek" />
      </body>
    </html>
  </xsl:template>

  <xsl:template match="tns:dayofTheweek">
    <h2><xsl:value-of select="@name" /> (<xsl:value-of select="@datetime" />)</h2>
    <table>
      <thead>
        <tr>
          <th>Godzina</th>
          <th>Przedmiot</th>
          <th>Nauczyciel</th>
          <th>Typ</th>
        </tr>
      </thead>
      <tbody>
        <xsl:apply-templates select="*" />
      </tbody>
    </table>
    <br/>
  </xsl:template>

  <xsl:template match="tns:lesson | tns:break | tns:substitute | tns:freeoflessons">
    <tr>
      <td><xsl:value-of select="tns:fromTo" /></td>
      <td><xsl:value-of select="tns:subject" /></td>
      <td><xsl:value-of select="tns:teacher" /></td>
      <td>
        <xsl:choose>
          <xsl:when test="self::tns:break">Okienko</xsl:when>
          <xsl:when test="self::tns:substitute">Zastępstwo</xsl:when>
          <xsl:when test="self::tns:freeoflessons">Wolne</xsl:when>
          <xsl:otherwise>Lekcja</xsl:otherwise>
        </xsl:choose>
      </td>
    </tr>
  </xsl:template>

</xsl:stylesheet>